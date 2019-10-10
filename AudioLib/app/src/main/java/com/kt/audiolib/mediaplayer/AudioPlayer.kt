package com.kt.audiolib.mediaplayer

import android.media.AudioManager
import android.media.MediaPlayer

/*
 * @author : created by CC
 * time    : 2019/9/27  15:21
 * desc    : 1，播放音频
 *           2，对外发送各种类型的事件
 */

class AudioPlayer : MediaPlayer.OnCompletionListener, MediaPlayer.OnBufferingUpdateListener,
    MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener{

    private val TIME_MSG = 0x01
    private val TIME_INVAL = 100

    override fun onCompletion(p0: MediaPlayer?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBufferingUpdate(p0: MediaPlayer?, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPrepared(p0: MediaPlayer?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError(p0: MediaPlayer?, p1: Int, p2: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}