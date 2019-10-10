package com.kt.audiolib.mediaplayer

import android.content.res.AssetFileDescriptor
import android.media.MediaPlayer

/*
 * @author : created by CC
 * time    : 2019/9/27  10:31
 * desc    : 带状态的MediaPlayer
 */

class CustomMediaPlayer : MediaPlayer(), MediaPlayer.OnCompletionListener {

    enum class Status {
        IDELL, INITIALIZED, STARTED, PAUSED, STOPPED, COMPLETED
    }

    private lateinit var listener: OnCompletionListener
    private var mState = Status.IDELL

    init {
        mState = Status.IDELL
        super.setOnCompletionListener(this)
    }

    /***/
    override fun reset() {
        super.reset()
        mState = Status.IDELL
    }

    /***/
    override fun setDataSource(afd: AssetFileDescriptor) {
        super.setDataSource(afd)
        mState = Status.INITIALIZED
    }

    /***/
    override fun start() {
        super.start()
        mState = Status.STARTED
    }

    /***/
    override fun pause() {
        super.pause()
        mState = Status.PAUSED
    }

    /***/
    override fun stop() {
        super.stop()
        mState = Status.STOPPED
    }


    /***/
    override fun onCompletion(p0: MediaPlayer?) {
        mState = Status.COMPLETED
    }

    fun getState(): Status = mState

    fun isComplete(): Boolean = mState == Status.COMPLETED

    fun setCompleteListener(listener: OnCompletionListener) {
        this.listener = listener
    }

}