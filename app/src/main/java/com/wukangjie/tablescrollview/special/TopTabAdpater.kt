package com.wukangjie.tablescrollview.special

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.wukangjie.tablescrollview.R

class TopTabAdpater(private val context: Context) : RecyclerView.Adapter<TopTabAdpater.TabViewHolder>() {
    override fun getItemCount(): Int {
        return if (null == datas) 0 else datas!!.size
    }

    private var datas: List<String>? = null


    fun setDatas(datas: List<String>) {
        this.datas = datas
        notifyDataSetChanged()
    }


    @NonNull
    override fun onCreateViewHolder(@NonNull viewGroup: ViewGroup, i: Int): TabViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_item_scroll, viewGroup, false)
        return TabViewHolder(view)
    }

    override fun onBindViewHolder(@NonNull tabViewHolder: TabViewHolder, i: Int) {
        tabViewHolder.mTabTv.text = datas!![i]
    }

    inner class TabViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {

        var mTabTv: TextView

        init {
            mTabTv = itemView.findViewById(R.id.tv_right_scroll)
        }
    }

}
