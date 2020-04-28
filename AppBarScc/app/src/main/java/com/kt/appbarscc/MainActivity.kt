package com.kt.appbarscc

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTvClick.setOnClickListener {

            DialogUtil.show(this,"提示","什么情况").setPositiveButton("确定",object:DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {

                }

            }).create().show()
//            builder.setIcon(R.mipmap.ic_launcher)
//                .setTitle()
//                .setMessage()
//                .create()
//                .show()
        }
    }
}
