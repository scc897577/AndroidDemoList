package com.kt.audiolib.mediaplayer

import android.content.Context
import android.media.AudioManager

/*
 * @author : created by CC
 * time    : 2019/9/29  9:27
 * desc    : 获得音频焦点相关类
 *           音频焦点监听器
 */

class AudioFocusManager(
    var context: Context,
    var mAudioFocusListener: AudioFocusListener,
    var audioManager: AudioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
) : AudioManager.OnAudioFocusChangeListener {


    fun requestAudioFocus(): Boolean =
        audioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN) ==
                AudioManager.AUDIOFOCUS_REQUEST_GRANTED

    fun abandonAudioFocus() {
        audioManager.abandonAudioFocus(this)
    }


    override fun onAudioFocusChange(focusChange: Int) {
        when (focusChange) {
            // 重新获得焦点
            AudioManager.AUDIOFOCUS_GAIN -> {
                if (mAudioFocusListener != null) mAudioFocusListener.audioFocusGrant()
            }
            // 永久丢失焦点，如被其他播放器抢占
            AudioManager.AUDIOFOCUS_LOSS -> {
                if (mAudioFocusListener != null) mAudioFocusListener.audioFocusLoss()
            }
            // 短暂丢失焦点，如来电
            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> {
                if (mAudioFocusListener != null) mAudioFocusListener.audioFocusLossTransient()
            }
            // 瞬间丢失焦点，如通知
            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK -> {
                if (mAudioFocusListener != null) mAudioFocusListener.audioFocusLossDuck()
            }
        }
    }

    /** 音频焦点改变,接口回调 */
    interface AudioFocusListener {
        //获得焦点回调处理
        fun audioFocusGrant()

        //永久失去焦点回调处理
        fun audioFocusLoss()

        //短暂失去焦点回调处理
        fun audioFocusLossTransient()

        //瞬间失去焦点回调
        fun audioFocusLossDuck()
    }
}