package com.kt.block

import android.app.Notification
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.app.NotificationCompat
import android.app.NotificationManager
import android.app.NotificationChannel
import android.os.Build
import android.content.Context.NOTIFICATION_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.app.PendingIntent
import android.content.Context
import android.content.Intent


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        SystemClock.sleep(1000)
        mTv.setOnClickListener {
            showNotifictionIcon(this, RowMsgBean("11111", "222222"))
        }
    }


    fun showNotifictionIcon(context: Context, mRowMsgBean: RowMsgBean) {

        val intent = Intent(context, MessageListActivity::class.java)
        val intentPend = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder: NotificationCompat.Builder
        //判断是否是8.0Android.O
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val chan1 = NotificationChannel("static", "Primary Channel", NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(chan1)
            builder = NotificationCompat.Builder(context, "static")
        } else {
            builder = NotificationCompat.Builder(context)
        }

        builder.setDefaults(NotificationCompat.DEFAULT_SOUND)//设置通知铃声
        val notification = builder.setTicker("您有新的消息")
            .setDefaults(Notification.DEFAULT_ALL)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setWhen(System.currentTimeMillis())
            .setContentIntent(intentPend)
            .setContentTitle(mRowMsgBean.title)
            .setContentText(mRowMsgBean.text)
            .setAutoCancel(true)
            .build()

        if (manager != null && notification != null) {
            manager.notify(1, notification)
        }
    }

}
