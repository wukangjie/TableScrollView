package com.wukangjie.tablescrollview.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.wukangjie.tablescrollview.R


class TabAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {
    override fun getItemCount(): Int {
        return if (mList == null) 0 else mList!!.size
    }

    private var mContext: Context? = null
    private var mList: List<String>? = null
    private var type: Int = 0
    private val TITLE = 1
    private val CONTENT = 2


    constructor(mContext: Context, mList: List<String>) {
        this.mContext = mContext
        this.mList = mList
    }

    constructor(mContext: Context, mList: List<String>, type: Int) {
        this.mContext = mContext
        this.mList = mList
        this.type = type
    }

    constructor() : super()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TITLE) {
            val itemView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_good_name, parent, false)
            return GuapaiViewHolder(itemView)
        } else {
            val itemView = LayoutInflater.from(mContext).inflate(R.layout.table_right_item, parent, false)
            return ContentViewHolder(itemView)
        }

    }

    override fun onBindViewHolder(@NonNull holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is GuapaiViewHolder) {
            val mViewHolder = holder as GuapaiViewHolder
            mViewHolder.setmStock(mList!![position])
            mViewHolder.refreshView()
        } else if (holder is ContentViewHolder) {
            val mViewHolder = holder as ContentViewHolder
            mViewHolder.setmStock(mList!![position])
            mViewHolder.refreshView()
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (type == 0) {
            TITLE
        } else {
            CONTENT
        }
    }

    internal inner class GuapaiViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var tv_goodname: TextView
        lateinit var mStock: String

        init {
            tv_goodname = view.findViewById(R.id.tv_name) as TextView
        }

        fun setmStock(mStock: String) {
            this.mStock = mStock
        }

        fun refreshView() {
            //公司名称
            tv_goodname.text = mStock

        }
    }

    internal inner class ContentViewHolder(convertView: View) : RecyclerView.ViewHolder(convertView) {

        var tv_barcode: TextView
        var tv_icon: TextView
        var tv_category: TextView
        var tv_spec: TextView
        var tv_unit: TextView
        var tv_supplier: TextView
        var tv_sale_money: TextView
        var tv_income_money: TextView
        var tv_keep: TextView
        var tv_intime: TextView
        var tv_online: TextView
        var tv_time: TextView
        var tv_z01: TextView
        lateinit var mStock: String

        init {
            //当前价
            tv_barcode = convertView.findViewById(R.id.tv_table_content_right_item0) as TextView
            //涨跌幅
            tv_icon = convertView.findViewById(R.id.tv_table_content_right_item1) as TextView
            //成交额
            tv_category = convertView.findViewById(R.id.tv_table_content_right_item2) as TextView
            //成交量
            tv_spec = convertView.findViewById(R.id.tv_table_content_right_item3) as TextView
            //涨跌额
            tv_unit = convertView.findViewById(R.id.tv_table_content_right_item4) as TextView
            //营收
            tv_supplier = convertView.findViewById(R.id.tv_table_content_right_item5) as TextView
            //净利润
            tv_sale_money = convertView.findViewById(R.id.tv_table_content_right_item6) as TextView
            //流通股本
            tv_income_money = convertView.findViewById(R.id.tv_table_content_right_item7) as TextView
            //流通市值
            tv_keep = convertView.findViewById(R.id.tv_table_content_right_item8) as TextView
            //总股本
            tv_intime = convertView.findViewById(R.id.tv_table_content_right_item9) as TextView
            //总市值
            tv_online = convertView.findViewById(R.id.tv_table_content_right_item10) as TextView
            //市盈率
            tv_time = convertView.findViewById(R.id.tv_table_content_right_item11) as TextView
            //每股收益
            tv_z01 = convertView.findViewById(R.id.tv_table_content_right_item12) as TextView
        }

        fun setmStock(mStock: String) {
            this.mStock = mStock
        }

        fun refreshView() {
            tv_barcode.text = mStock


            tv_icon.text = mStock
            tv_category.text = mStock
            tv_spec.text = mStock
            tv_unit.text = mStock

            tv_supplier.text = mStock



            tv_sale_money.text = mStock


            tv_keep.text = mStock



            tv_intime.text = mStock


            tv_income_money.text = mStock

            tv_online.text = mStock

            tv_time.text = mStock

            tv_z01.text = mStock

        }
    }

    fun add(mPriceEntityList: List<String>) {
        mList = mPriceEntityList
        notifyDataSetChanged()
    }

}
