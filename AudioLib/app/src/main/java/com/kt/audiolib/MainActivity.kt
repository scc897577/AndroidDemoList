package com.kt.audiolib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kt.audiolib.bean.*
import com.kt.audiolib.mediaplayer.AudioController
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {

//    private var mAudioBean = AudioBean()

    private var mList = mutableListOf<AudioBean>()


    private val mUrl1 = "https://tour-file.oss-cn-qingdao.aliyuncs.com/spot/T/2024/ebb3f8c76f2246eb91447f60332759ae"
    private val mUrl2 = "https://tour-file.oss-cn-qingdao.aliyuncs.com/spot/T/2024/f009a6af63104fe59e511786bd49ec9e"
    private val mUrl3 = "https://tour-file.oss-cn-qingdao.aliyuncs.com/spot/T/2024/78d7246257ce4fe59ceb44f7154beea7"
    private val mUrl4 = "https://tour-file.oss-cn-qingdao.aliyuncs.com/spot/T/2024/f525c23eeca24863b333e0cf6582270a"
    private val mUrl5 = "https://tour-file.oss-cn-qingdao.aliyuncs.com/spot/T/2024/03b41c85a92a41a892c2c3273044117e"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (EventBus().isRegistered(this).not()) {
            EventBus.getDefault().register(this)
        }

        init()
    }

    private fun init() {
        mList.add(AudioBean(mUrl1, "0"))
        mList.add(AudioBean(mUrl2, "1"))
        mList.add(AudioBean(mUrl3, "2"))
        mList.add(AudioBean(mUrl4, "3"))
        mList.add(AudioBean(mUrl5, "4"))

        AudioController.instance.setQueue(mList)
        AudioController.instance.play()

    }

    /** 监听加载事件 */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onAudioLoadEvent(event: AudioLoadEvent) {
        //监听加载事件
        val audioBean = event.audioBean
        AudioController.instance.resume()
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onAudioStartEvent(event: AudioStartEvent) {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onAudioPauseEvent(event: AudioPauseEvent) {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onAudioReleaseEvent(event: AudioReleaseEvent) {

    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
