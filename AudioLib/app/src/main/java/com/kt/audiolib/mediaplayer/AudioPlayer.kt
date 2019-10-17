package com.kt.audiolib.mediaplayer

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.wifi.WifiManager
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.os.PowerManager
import android.util.Log
import com.kt.audiolib.app.AudioHelper
import com.kt.audiolib.bean.*
import org.greenrobot.eventbus.EventBus
import java.lang.Exception

/*
 * @author : created by CC
 * time    : 2019/9/27  15:21
 * desc    : 1，播放音频
 *           2，对外发送各种类型的事件
 */

class AudioPlayer : MediaPlayer.OnCompletionListener, MediaPlayer.OnBufferingUpdateListener,
    MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, AudioFocusManager.AudioFocusListener {

    private val TAG = "AudioPlayer"
    private val TIME_MSG = 0x01
    private val TIME_INVAL = 100
    private var isPauseByFocusLossTransient = false


    //真正负责音频的播放
    private var mCustomMediaPlayer: CustomMediaPlayer? = null
    //增强后台音频保活能力
    private var mWifiLock: WifiManager.WifiLock? = null
    //音频焦点监听器
    private var mAudioFocusManager: AudioFocusManager? = null

    private val mHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                TIME_MSG -> {
                }
                TIME_INVAL -> {
                }
            }
        }
    }

    /** 初始化 */
    init {
        mCustomMediaPlayer = CustomMediaPlayer()
        mCustomMediaPlayer?.setWakeMode(AudioHelper.getContext(), PowerManager.PARTIAL_WAKE_LOCK)
        mCustomMediaPlayer?.setAudioStreamType(AudioManager.STREAM_MUSIC)
        mCustomMediaPlayer?.setOnCompletionListener(this)
        mCustomMediaPlayer?.setOnPreparedListener(this)
        mCustomMediaPlayer?.setOnBufferingUpdateListener(this)
        mCustomMediaPlayer?.setOnErrorListener(this)
        //初始化WifiLock
        mWifiLock =
            (AudioHelper.getContext().applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager).createWifiLock(
                WifiManager.WIFI_MODE_FULL, TAG
            )
        //初始化音频焦点监听器
        mAudioFocusManager = AudioFocusManager(AudioHelper.getContext(), this)
    }

    /**
     * 对外提供的加载方法
     */
    fun load(audioBean: AudioBean) {
        try {
            //正常加载逻辑
            mCustomMediaPlayer?.reset()
            mCustomMediaPlayer?.setDataSource(audioBean.mUrl)
            mCustomMediaPlayer?.prepareAsync()
            //对外发送load事件
            EventBus.getDefault().post(AudioLoadEvent(audioBean))
        } catch (e: Exception) {
            //对外发送error事件
            EventBus.getDefault().post(AudioErrorEvent())
        }
    }


    /**
     * 对外提供的暂停
     */
    fun pause() {
        if (getStatus() == CustomMediaPlayer.Status.STOPPED) {
            mCustomMediaPlayer?.pause()
            //释放音频焦点wifiLock
            if (mWifiLock != null && mWifiLock!!.isHeld) {
                mWifiLock?.release()
            }
            //释放音频焦点
            if (mAudioFocusManager != null) {
                mAudioFocusManager!!.abandonAudioFocus()
            }
            //发送暂停事件
            EventBus.getDefault().post(AudioPauseEvent())
        }
    }


    /**
     * 对外提供的恢复
     */
    fun resume() {
        if (getStatus() == CustomMediaPlayer.Status.PAUSED) {
            //复用start
            start()
        }
    }

    /**
     * 清空播放器占用资源
     */
    fun release() {
        if (mCustomMediaPlayer == null) {
            return
        }
        mCustomMediaPlayer?.release()
        mCustomMediaPlayer = null
        if (mAudioFocusManager != null) {
            mAudioFocusManager!!.abandonAudioFocus()
        }
        if (mWifiLock != null && mWifiLock!!.isHeld) {
            mWifiLock!!.release()
        }
        mWifiLock = null
        mAudioFocusManager = null
        //发送release销毁事件
        EventBus.getDefault().post(AudioReleaseEvent())
    }


    /** 播放完毕回调 */
    override fun onCompletion(p0: MediaPlayer?) {
        EventBus.getDefault().post(AudioCompleteEvent())
    }

    /** 缓存进度回调 */
    override fun onBufferingUpdate(p0: MediaPlayer?, p1: Int) {

    }

    /** 准备完毕，进入播放 */
    override fun onPrepared(p0: MediaPlayer?) {
        start()
    }

    /** 播放出错回调 */
    override fun onError(p0: MediaPlayer?, p1: Int, p2: Int): Boolean {
        //此处AudioErrorEvent实体类和在load时实体类为同一个类，需要区分（如有用到）
        EventBus.getDefault().post(AudioErrorEvent())
        // return true Error 事件一旦发生就不需要在回调onCompletion方法了，代表自行处理异常
        return true
    }

    /** 再次获取音频焦点 */
    override fun audioFocusGrant() {
        //设置音量
        setVolume(1.0f, 1.0f)
        if (isPauseByFocusLossTransient) {
            //再次获得焦点时，恢复播放
            resume()
        }
        isPauseByFocusLossTransient = false
    }

    /** 永久失去了音频焦点 */
    override fun audioFocusLoss() {
        pause()
    }

    /** 短暂性失去焦点 */
    override fun audioFocusLossTransient() {
        pause()
        isPauseByFocusLossTransient = true
    }

    /** 瞬间失去焦点 */
    override fun audioFocusLossDuck() {
        //声音减半
        setVolume(0.5f, 0.5f)
    }

    // 设置音量
    private fun setVolume(leftVol: Float, rightVol: Float) {
        if (mCustomMediaPlayer != null) {
            mCustomMediaPlayer!!.setVolume(leftVol, rightVol)
        }
    }

    /** 获取播放器当前的状态 */
    fun getStatus(): CustomMediaPlayer.Status {
        if (mCustomMediaPlayer != null) {
            return mCustomMediaPlayer!!.getState()
        }
        return CustomMediaPlayer.Status.STOPPED
    }

    // 内部开始播放
    private fun start() {
        if (mAudioFocusManager?.requestAudioFocus()!!.not()) {
            Log.e(TAG, "获取音频焦点失败")
            return
        }
        mCustomMediaPlayer?.start()
        mWifiLock?.acquire()
        //对外发送start事件
        EventBus.getDefault().post(AudioStartEvent())
    }
}