package com.kt.crashdemo

import android.app.AlertDialog
import android.content.Context
import android.os.Build

class DialogUtilGlobal {

    companion object {
        fun buildDialog(context: Context, title: String, msg: String): AlertDialog.Builder {
            val builder: AlertDialog.Builder =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog_Alert)
                } else {
                    AlertDialog.Builder(context)
                }
            builder.setIcon(R.mipmap.ic_launcher)
            builder.setTitle(title)
            builder.setMessage(msg)
            builder.setPositiveButton("确认") { _, _ -> }.create()
            return builder
        }

        fun buildDialog(context: Context, title: String): AlertDialog.Builder {
            val builder: AlertDialog.Builder =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog_Alert)
                } else {
                    AlertDialog.Builder(context)
                }
            builder.setIcon(R.mipmap.ic_launcher)
            builder.setTitle(title)
            return builder
        }
    }

}