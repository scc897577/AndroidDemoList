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

    /** 空状态 */
    override fun reset() {
        super.reset()
        mState = Status.IDELL
    }

    /** 初始化状态 */
    override fun setDataSource(afd: AssetFileDescriptor) {
        super.setDataSource(afd)
        mState = Status.INITIALIZED
    }

    /** 开始状态 */
    override fun start() {
        super.start()
        mState = Status.STARTED
    }

    /** 暂停状态 */
    override fun pause() {
        super.pause()
        mState = Status.PAUSED
    }

    /** 停止状态 */
    override fun stop() {
        super.stop()
        mState = Status.STOPPED
    }

    /** 完成状态 */
    override fun onCompletion(p0: MediaPlayer?) {
        mState = Status.COMPLETED
    }

    /** 获得当前的播放状态 */
    fun getState(): Status = mState

    /** 是否已经播放完成 */
    fun isComplete(): Boolean = mState == Status.COMPLETED

    /** 是否播放完成的监听 */
    fun setCompleteListener(listener: OnCompletionListener) {
        this.listener = listener
    }

}