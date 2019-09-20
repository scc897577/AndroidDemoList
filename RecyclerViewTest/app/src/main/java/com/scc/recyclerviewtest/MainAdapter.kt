package com.scc.recyclerviewtest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_main.view.*

class MainAdapter(context: Context) : BaseRecyclerViewAdapter<Bean, MainAdapter.ViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_main, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        holder.itemView.mTv.text = model.str
    }

    /**
     * 给adapter增加一条数据
     */
    fun addData(position: Int) {
        dataList.add(Bean("添加了$position"))
        notifyItemInserted(position)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)


    /**
     * 07/04 17:01:30: Launching CRLY
    Device emulator-5554disconnected, monitoring stopped.
    $ adb push F:\work\tour_android\CRLY\build\outputs\apk\itour\debug\CRLY-itour-debug.apk /data/local/tmp/com.chuangrong.itour
    Error while Installing APK
     */
}