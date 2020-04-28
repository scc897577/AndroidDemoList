package com.kt.crashdemo

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_scc.*
import kotlin.math.abs


class SccActivity : BaseActivity() ,Ai{
    override fun aa() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scc)

        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayShowTitleEnabled(false)

        app_bar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            //上滑最大255，下滑最小0  0~255
            val maxScroll = appBarLayout?.totalScrollRange
            val percentage = abs(verticalOffset).toFloat() / maxScroll!!.toFloat()
            if (percentage == 0f) {
                toolbar.visibility = View.GONE
                toolbar_layout.visibility = View.VISIBLE
            } else {
                toolbar.visibility = View.VISIBLE
                toolbar.alpha = percentage
            }
        })
    }
}
