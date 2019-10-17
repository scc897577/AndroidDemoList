package com.kt.audiolib.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.kt.audiolib.app.AudioHelper
import com.kt.audiolib.bean.AudioBean
import com.kt.audiolib.mediaplayer.AudioController
import java.time.temporal.TemporalAdjusters.previous
import android.text.TextUtils
import android.content.BroadcastReceiver
import android.content.Context
import com.kt.audiolib.bean.AudioReleaseEvent
import org.greenrobot.eventbus.ThreadMode
import org.greenrobot.eventbus.Subscribe
import com.kt.audiolib.bean.AudioStartEvent
import com.kt.audiolib.bean.AudioPauseEvent
import com.kt.audiolib.bean.AudioLoadEvent
import org.greenrobot.eventbus.EventBus
import android.content.IntentFilter


/*
 * @author : created by CC
 * time    : 2019/10/17  10:10
 * desc    :
 */

//class MusicService : Service() {
//
//    companion object {
//        /** 常量 */
//        private val DATA_AUDIOS = "AUDIOS"
//        private val ACTION_START = "ACTION_START"
//
//        fun startMusicService(audioBeans: ArrayList<AudioBean>) {
//            val intent = Intent(AudioHelper.getContext(), MusicService::class.java)
//            intent.action = ACTION_START
//            //还需要传list数据进来
//            intent.putExtra(DATA_AUDIOS, audioBeans)
//            AudioHelper.getContext().startService(intent)
//        }
//    }
//
//
//    /** data */
//    private var mAudioBeans = mutableListOf<AudioBean>()
//
//
//    override fun onBind(p0: Intent?): IBinder? = null
//
//    override fun onCreate() {
//        super.onCreate()
//        EventBus.getDefault().register(this)
//        registerBroadcastReceiver()
//    }
//
//    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
//        mAudioBeans = intent.getSerializableExtra(DATA_AUDIOS) as ArrayList<AudioBean>
//        if (ACTION_START.equals(intent.action)) {
//            //开始播放
//            playMusic()
//            //初始化前台Notification
//        }
//        return super.onStartCommand(intent, flags, startId)
//    }
//
//    private fun playMusic() {
//        AudioController.instance.setQueue(mAudioBeans)
//        AudioController.instance.play()
//    }
//
//    private fun registerBroadcastReceiver() {
//        if (mReceiver == null) {
//            mReceiver = NotificationReceiver()
//            val filter = IntentFilter()
//            filter.addAction(NotificationReceiver.ACTION_STATUS_BAR)
//            registerReceiver(mReceiver, filter)
//        }
//    }
//
//    private fun unRegisterBroadcastReceiver() {
//        if (mReceiver != null) {
//            unregisterReceiver(mReceiver)
//        }
//    }
//
//    fun onNotificationInit() {
//        //service与Notification绑定
//        startForeground(NOTIFICATION_ID, NotificationHelper.getInstance().getNotification())
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        EventBus.getDefault().unregister(this)
//        unRegisterBroadcastReceiver()
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    fun onAudioLoadEvent(event: AudioLoadEvent) {
//        //更新notifacation为load状态
//        NotificationHelper.getInstance().showLoadStatus(event.mAudioBean)
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    fun onAudioPauseEvent(event: AudioPauseEvent) {
//        //更新notifacation为暂停状态
//        NotificationHelper.getInstance().showPauseStatus()
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    fun onAudioStartEvent(event: AudioStartEvent) {
//        //更新notifacation为播放状态
//        NotificationHelper.getInstance().showPlayStatus()
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    fun onAudioFavouriteEvent(event: AudioFavouriteEvent) {
//        //更新notifacation收藏状态
//        NotificationHelper.getInstance().changeFavouriteStatus(event.isFavourite)
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    fun onAudioReleaseEvent(event: AudioReleaseEvent) {
//        //移除notifacation
//    }
//
//    /**
//     * 接收Notification发送的广播
//     */
////    class NotificationReceiver : BroadcastReceiver() {
////
////        fun onReceive(context: Context, intent: Intent?) {
////            if (intent == null || TextUtils.isEmpty(intent.action)) {
////                return
////            }
////            val extra = intent.getStringExtra(EXTRA)
////            when (extra) {
//////                EXTRA_PLAY ->
//////                    //处理播放暂停事件,可以封到AudioController中
//////                    AudioController.getInstance().playOrPause()
//////                EXTRA_PRE -> AudioController.getInstance().previous() //不管当前状态，直接播放
//////                EXTRA_NEXT -> AudioController.getInstance().next()
//////                EXTRA_FAV -> AudioController.getInstance().changeFavourite()
////            }
////        }
////
////        companion object {
////            val ACTION_STATUS_BAR = AudioHelper.getContext().packageName + ".NOTIFICATION_ACTIONS"
////            val EXTRA = "extra"
////            val EXTRA_PLAY = "play_pause"
////            val EXTRA_NEXT = "play_next"
////            val EXTRA_PRE = "play_previous"
////            val EXTRA_FAV = "play_favourite"
////        }
////    }
//
//}