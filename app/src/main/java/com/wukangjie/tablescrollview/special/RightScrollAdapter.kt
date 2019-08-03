package com.wukangjie.tablescrollview.special

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.wukangjie.tablescrollview.R


class RightScrollAdapter(private val context: Context) : RecyclerView.Adapter<RightScrollAdapter.ScrollViewHolder>() {
    override fun getItemCount(): Int {
        return if (null == rightDatas) 0 else rightDatas!!.size
    }

    private var rightDatas: List<String>? = null


    fun setDatas(rightDatas: List<String>) {
        this.rightDatas = rightDatas
        notifyDataSetChanged()
    }


    @NonNull
    override fun onCreateViewHolder(@NonNull viewGroup: ViewGroup, i: Int): ScrollViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_item_scroll, viewGroup, false)
        return ScrollViewHolder(view)
    }

    override fun onBindViewHolder(@NonNull scrollViewHolder: ScrollViewHolder, i: Int) {
        scrollViewHolder.mTvScrollItem.text = rightDatas!![i]
    }

    inner class ScrollViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {

        var mTvScrollItem: TextView

        init {
            mTvScrollItem = itemView.findViewById(R.id.tv_right_scroll)

        }
    }
}
