package com.scc.recyclerviewtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter: MainAdapter
    private var mList = mutableListOf<Bean>()
    private var positionAdd = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.mRecyclerView)

        for (i in 0 until 50) {
            mList.add(Bean(i.toString()))
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = MainAdapter(this)
        mAdapter.setData(mList)
        recyclerView.adapter = mAdapter
        mAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Bean> {
            override fun onItemClick(item: Bean, position: Int) {
                positionAdd = position
                val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                intent.putExtra("position", position)
                startActivity(intent)
            }
        })

        mBtnAdd.setOnClickListener {
            if (isInteger(mList[positionAdd].str)) {
                mList[positionAdd].str = (mList[positionAdd].str.toInt() + 1).toString()
            } else {
                mList[positionAdd].str = "0"
            }
            mAdapter.setData(mList)
            mAdapter.notifyDataSetChanged()
        }

        mBtnAddItem.setOnClickListener {
            mAdapter.addData(mList.size)
            smoothMoveToPosition(mRecyclerView, mList.size)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun event(eb: EventBean) {
        mList[eb.position].str = eb.str
        mAdapter.setData(mList)
        mAdapter.notifyDataSetChanged()
    }


    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }


    private fun isInteger(str: String): Boolean {
        val pattern = Pattern.compile("^[-\\+]?[\\d]*$")
        return pattern.matcher(str).matches()
    }


    //目标项是否在最后一个可见项之后
    private var mShouldScroll: Boolean = false
    //记录目标项位置
    private var mToPosition: Int = 0


    /**
     * 滑动到指定位置
     */
    private fun smoothMoveToPosition(mRecyclerView: RecyclerView, position: Int) {
        // 第一个可见位置
        val firstItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(0))
        // 最后一个可见位置
        val lastItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(mRecyclerView.childCount - 1))
        if (position < firstItem) {
            // 第一种可能:跳转位置在第一个可见位置之前，使用smoothScrollToPosition
            mRecyclerView.smoothScrollToPosition(position)
        } else if (position <= lastItem) {
            // 第二种可能:跳转位置在第一个可见位置之后，最后一个可见项之前
            val movePosition = position - firstItem
            if (movePosition >= 0 && movePosition < mRecyclerView.childCount) {
                val top = mRecyclerView.getChildAt(movePosition).top
                // smoothScrollToPosition 不会有效果，此时调用smoothScrollBy来滑动到指定位置
                mRecyclerView.smoothScrollBy(0, top)
            }
        } else {
            // 第三种可能:跳转位置在最后可见项之后，则先调用smoothScrollToPosition将要跳转的位置滚动到可见位置
            // 再通过onScrollStateChanged控制再次调用smoothMoveToPosition，执行上一个判断中的方法
            mRecyclerView.smoothScrollToPosition(position)
            mToPosition = position
            mShouldScroll = true
        }
    }
}
