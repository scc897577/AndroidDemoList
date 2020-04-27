package com.kt.appbarscc

import android.app.AlertDialog
import android.content.Context
import android.os.Build

class DialogUtil {
    companion object{

        fun show(context:Context,title:String,msg:String):AlertDialog.Builder{
            val builder: AlertDialog.Builder =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog_Alert)
                } else {
                    AlertDialog.Builder(context)
                }
            builder.setIcon(R.mipmap.ic_launcher)
            builder.setTitle(title)
            builder.setMessage(msg)
            return builder
        }
    }
}