package com.kt.audiolib.exception

import java.lang.RuntimeException

/*
 * @author : created by CC
 * time    : 2019/10/16  14:29
 * desc    : 播放队列为空异常
 */
class AudioQueueEmptyException(error: String) : RuntimeException(error)