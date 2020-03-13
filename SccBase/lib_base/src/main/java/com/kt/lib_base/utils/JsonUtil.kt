package com.kt.lib_base.utils


import com.google.gson.GsonBuilder
import okhttp3.MediaType
import okhttp3.RequestBody

object JsonUtil {
    //将map转换为Json
    fun mapToJson(vararg args: String): RequestBody {
        val gson = GsonBuilder().disableHtmlEscaping().create()
        val map = HashMap<String, String>()
        for (i in args.indices step 2) {
            map[args[i]] = args[i + 1]
        }
        val json =gson.toJson(map)
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)
    }
}