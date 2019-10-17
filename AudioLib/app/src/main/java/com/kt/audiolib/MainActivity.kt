package com.kt.audiolib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kt.audiolib.bean.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {

//    private var mAudioBean = AudioBean()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (EventBus().isRegistered(this).not()) {
            EventBus.getDefault().register(this)
        }

        init()
    }

    private fun init() {

    }

    /** 监听加载事件 */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onAudioLoadEvent(event: AudioLoadEvent) {
        //监听加载事件
//        mAudioBean = event.audioBean
        showLoadingView()
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onAudioStartEvent(event: AudioStartEvent) {
        showPlayView()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onAudioPauseEvent(event: AudioPauseEvent) {
        showPauseView()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onAudioReleaseEvent(event: AudioReleaseEvent) {

    }

    private fun showLoadingView() {

    }
    private fun showPlayView() {

    }
    private fun showPauseView() {

    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
