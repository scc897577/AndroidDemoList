package com.kt.audiolib.mediaplayer

import com.kt.audiolib.bean.AudioBean
import org.greenrobot.eventbus.EventBus
import kotlin.random.Random
import com.kt.audiolib.bean.AudioBean
import com.kt.audiolib.exception.AudioQueueEmptyException


/*
 * @author : created by CC
 * time    : 2019/10/16  10:37
 * desc    : 控制播放逻辑类
 */

class AudioController {

    /** 播放方式 */
    enum class PlayMode {
        /** 列表循环，随机，单曲循环 */
        LOOP,
        RANDOM,
        REPEAT
    }

    /** 双重校验锁式单例模式 */
    companion object {
        private var mAudioPlayer = AudioPlayer()                 //核心播放器
        private var mQueue = mutableListOf<AudioBean>()         //歌曲队列
        private var mQueueIndex = 0                             //当前播放歌曲索引
        private var mPlayMode = PlayMode.LOOP                   //循环模式
        val instance: AudioController by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            AudioController()
        }
    }

    fun getQueue(): MutableList<AudioBean> {
        return mQueue
    }

    /** 设置播放队列 */
    fun setQueue(queue: MutableList<AudioBean>) {
        setQueue(queue, 0)
    }

    /** 设置播放队列和应播放索引 */
    fun setQueue(queue: MutableList<AudioBean>, queueIndex: Int) {
        mQueue.addAll(queue)
        mQueueIndex = queueIndex
    }

    fun getPlayMode(): PlayMode {
        return mPlayMode
    }

    /** 对外提供设置播放模式 */
    fun setPlayMode(playMode: PlayMode) {
        mPlayMode = playMode
    }

    /** 设置播放下标 */
    fun setPlayIndex(index: Int) {
        if (mQueue.isNullOrEmpty()) {
            throw AudioQueueEmptyException("当前的播放队列为空，请先设置播放队列")
        }
        mQueueIndex = index
        play()
    }

    /** 获得播放下标 */
    fun getPlayIndex(): Int {
        return mQueueIndex
    }

    /** 对外提供是否播放中状态 */
    fun isStartState(): Boolean {
        return CustomMediaPlayer.Status.STARTED == getStatus()
    }

    /** 对外提供是否暂停状态 */
    fun isPauseState(): Boolean {
        return CustomMediaPlayer.Status.PAUSED == getStatus()
    }

    private fun getStatus(): CustomMediaPlayer.Status {
        return mAudioPlayer.getStatus()
    }

    /** 对外提供的play方法 */
    fun play() {
        val audioBean = getNowPlaying()
        mAudioPlayer.load(audioBean)
    }


    fun pause() {
        mAudioPlayer.pause()
    }

    fun resume() {
        mAudioPlayer.resume()
    }

    fun release() {
        mAudioPlayer.release()
        EventBus.getDefault().unregister(this)
    }

    /** 播放下一首 */
    fun next() {
        val audioBean = getNextPlaying()
        mAudioPlayer.load(audioBean)
    }

    /** 播放上一首 */
    fun previous() {
        val audioBean = getPreviousPlaying()
        mAudioPlayer.load(audioBean)
    }

    /** 暂停与播放的互相切换 */
    fun playOrPause() {
        if (isStartState()) {
            pause()
        } else if (isPauseState()) {
            resume()
        }
    }
    /** 添加单一歌曲到指定位置 */
    fun addAudio(bean: AudioBean) {
        this.addAudio(0, bean)
    }

    fun addAudio(index: Int, bean: AudioBean) {
        if (mQueue == null) {
            throw AudioQueueEmptyException("当前的播放队列为空")
        }
        val query = queryAudio(bean)
        if (query <= -1) {
            //没有添加过
            addCustomAudio(index, bean)
            setPlayIndex(index)
        } else {
            var abean = getNowPlaying()
            if (abean.id != bean.id) {
                //已经添加过，并且不在播放中
                setPlayIndex(query)
            }
        }
    }

    private fun addCustomAudio(index: Int, bean: AudioBean) {

    }

    private fun getNowPlaying(): AudioBean {
        return getPlaying(mQueueIndex)
    }

    /** 播放下一个 */
    private fun getNextPlaying(): AudioBean {
        when (mPlayMode) {
            PlayMode.LOOP -> {
                //mQueueIndex如此操作，mQueueIndex始终不会越界，并且始终会在mQueue.size中
                mQueueIndex = (mQueueIndex + 1) % mQueue.size
            }
            PlayMode.RANDOM -> {
                mQueueIndex = Random.nextInt(mQueue.size) % mQueue.size
            }
            PlayMode.REPEAT -> {

            }
        }
        return getPlaying()
    }

    private fun getPreviousPlaying(): AudioBean {
        when (mPlayMode) {
            PlayMode.LOOP -> {
                //mQueueIndex如此操作，mQueueIndex始终不会越界，并且始终会在mQueue.size中
                mQueueIndex = (mQueueIndex - 1) % mQueue.size
            }
            PlayMode.RANDOM -> {
                mQueueIndex = Random.nextInt(mQueue.size) % mQueue.size
            }
            PlayMode.REPEAT -> {

            }
        }
        return getPlaying()
    }

    private fun getPlaying(): AudioBean {
        if (mQueue.isNullOrEmpty() && mQueueIndex >= 0 && mQueueIndex < mQueue.size) {
            return mQueue[mQueueIndex]
        } else {
            throw AudioQueueEmptyException("当前的播放队列为空，请先设置播放队列")
        }
    }
    private fun queryAudio(bean: AudioBean): Int {
        return mQueue.indexOf(bean)
    }
}