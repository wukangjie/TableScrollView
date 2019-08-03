package com.wukangjie.tablescrollview.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.wukangjie.tablescrollview.R

class MainAdapter(var context: Context, var data: List<MainData>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    var onItemClick: OnItemClick? = null
        get() = field
        set(value) {
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.main_item, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.button.text = data[position].title
        holder.button.setOnClickListener({
            onItemClick?.onItemClick(position)
        })
    }


    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var button: Button = itemView.findViewById(R.id.main_button)

    }

    interface OnItemClick {
        fun onItemClick(position: Int)
    }
}