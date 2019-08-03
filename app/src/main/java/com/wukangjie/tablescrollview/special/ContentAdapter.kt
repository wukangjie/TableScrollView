package com.wukangjie.tablescrollview.special


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wukangjie.tablescrollview.R
import java.util.*


class ContentAdapter(private val context: Context) : RecyclerView.Adapter<ContentAdapter.ItemViewHolder>() {
    override fun getItemCount(): Int {
        return if (null == datas) 0 else datas!!.size
    }

    private var datas: List<Entity>? = null
    private val mViewHolderList = ArrayList<ItemViewHolder>()
    var offestX = 0
        private set
    private var onContentScrollListener: OnContentScrollListener? = null


    val viewHolderCacheList: List<ItemViewHolder>
        get() = mViewHolderList

    interface OnContentScrollListener {
        fun onScroll(offestX: Int)
    }

    fun setOnContentScrollListener(onContentScrollListener: OnContentScrollListener) {
        this.onContentScrollListener = onContentScrollListener
    }

    fun setDatas(datas: List<Entity>) {
        this.datas = datas
        notifyDataSetChanged()
    }


    @NonNull
    override fun onCreateViewHolder(@NonNull viewGroup: ViewGroup, i: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_item_content, viewGroup, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(@NonNull itemViewHolder: ItemViewHolder, i: Int) {
        itemViewHolder.tvLeftTitle.text = datas!![i].leftTitle
        //右边滑动部分
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL)
        itemViewHolder.rvItemRight!!.setLayoutManager(linearLayoutManager)
        itemViewHolder.rvItemRight!!.setHasFixedSize(true)
        val rightScrollAdapter = RightScrollAdapter(context)
        datas!![i].rightDatas?.let { rightScrollAdapter.setDatas(it) }
        itemViewHolder.rvItemRight!!.setAdapter(rightScrollAdapter)
        //缓存当前holder
        if (!mViewHolderList.contains(itemViewHolder)) {
            mViewHolderList.add(itemViewHolder)
        }
        //滑动单个ytem的rv时,右侧整个区域的联动
        itemViewHolder.horItemScrollview!!.setOnCustomScrollChangeListener(object :
            CustomHorizontalScrollView.OnCustomScrollChangeListener {
            override fun onCustomScrollChange(
                listener: CustomHorizontalScrollView,
                scrollX: Int,
                scrollY: Int,
                oldScrollX: Int,
                oldScrollY: Int
            ) {
                for (i in mViewHolderList.indices) {
                    val touchViewHolder = mViewHolderList[i]
                    if (touchViewHolder !== itemViewHolder) {
                        touchViewHolder.horItemScrollview!!.scrollTo(scrollX, 0)
                    }
                }
                //记录滑动距离,便于处理下拉刷新之后的还原操作
                if (null != onContentScrollListener) onContentScrollListener!!.onScroll(scrollX)
                offestX = scrollX
            }
        })
        //由于viewHolder的缓存,在1级缓存取出来是2个viewholder,并且不会被重新赋值,所以这里需要处理缓存的viewholder的位移
        itemViewHolder.horItemScrollview!!.viewTreeObserver.addOnGlobalLayoutListener {
            if (!itemViewHolder.isLayoutFinish) {
                itemViewHolder.horItemScrollview!!.scrollTo(offestX, 0)
                itemViewHolder.isLayoutFinish = true
            }
        }
    }

    inner class ItemViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvLeftTitle: TextView = itemView.findViewById(R.id.tv_left_title)
        var rvItemRight: RecyclerView? = itemView.findViewById(R.id.rv_item_right)
        var horItemScrollview: CustomHorizontalScrollView? = itemView.findViewById(R.id.hor_item_scrollview)
        var isLayoutFinish: Boolean = false//自定义字段,用于标记layout

    }
}
