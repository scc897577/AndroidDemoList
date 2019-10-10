package com.kt.audiolib.mediaplayer

import android.content.Context
import android.media.AudioManager

/*
 * @author : created by CC
 * time    : 2019/9/29  9:27
 * desc    : 获得音频焦点相关类
 */

class AudioFocusManager(
    var context: Context,
    var mAudioFocusListener: AudioFocusListener,
    var audioManager: AudioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
) : AudioManager.OnAudioFocusChangeListener {


    fun requestAudioFocus(): Boolean {

    }

    fun abandonAudioFocus() {

    }


    override fun onAudioFocusChange(focusChange: Int) {
        when (focusChange) {
            //重新获得焦点
            AudioManager.AUDIOFOCUS_GAIN ->{
                if (mAudioFocusListener!= null)
            }

        }
    }
}