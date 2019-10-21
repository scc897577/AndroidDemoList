package com.kt.audiolib.mediaplayer

import com.kt.audiolib.bean.AudioBean
import org.greenrobot.eventbus.EventBus
import kotlin.random.Random
import com.kt.audiolib.exception.AudioQueueEmptyException
import com.kt.audiolib.bean.AudioErrorEvent
import org.greenrobot.eventbus.ThreadMode
import org.greenrobot.eventbus.Subscribe
import com.kt.audiolib.bean.AudioCompleteEvent


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
        private var mAudioPlayer = AudioPlayer()                //核心播放器
        private var mQueue = mutableListOf<AudioBean>()         //歌曲队列
        private var mQueueIndex = 0                             //当前播放歌曲索引   -->  默认为0
        private var mPlayMode = PlayMode.LOOP                   //循环模式   -->  默认为列表循环
        val instance: AudioController by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            AudioController()
        }
    }

    init {
        if (EventBus.getDefault().isRegistered(this).not()) EventBus.getDefault().register(this)
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

    /** 获得播放设置 */
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
            val abean = getNowPlaying()
            if (abean.id != bean.id) {
                //已经添加过，并且不在播放中
                setPlayIndex(query)
            }
        }
    }

    private fun addCustomAudio(index: Int, bean: AudioBean) {

    }

    /** 得到当前播放的实体类 */
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
        return getPlaying(mQueueIndex)
    }

    /** 播放上一个 */
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
        return getPlaying(mQueueIndex)
    }

    private fun getPlaying(index: Int): AudioBean {
        if (mQueue.isNullOrEmpty().not() && index >= 0 && index < mQueue.size) {
            return mQueue[index]
        } else {
            throw AudioQueueEmptyException("当前的播放队列为空，请先设置播放队列")
        }
    }

    /** 根据实体查询下标 */
    private fun queryAudio(bean: AudioBean): Int {
        return mQueue.indexOf(bean)
    }


    //插放完毕事件处理
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onAudioCompleteEvent(event: AudioCompleteEvent) {
        next()
    }

    //播放出错事件处理
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onAudioErrorEvent(event: AudioErrorEvent) {
        next()
    }
}