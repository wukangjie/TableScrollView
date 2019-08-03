package com.wukangjie.tablescrollview.common

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.HorizontalScrollView


class SyncHorizontalScrollView : HorizontalScrollView {
    private var mView: View? = null
    private var mView1: View? = null
    private var mView2: View? = null
    private var mView3: View? = null
    private var mView4: View? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        //设置控件滚动监听，得到滚动的距离，然后让传进来的view也设置相同的滚动具体
        if (mView != null) {
            mView!!.scrollTo(l, t)
        }
        if (mView1 != null) {
            mView1!!.scrollTo(l, t)
        }
        if (mView2 != null) {
            mView2!!.scrollTo(l, t)
        }
        if (mView3 != null) {
            mView3!!.scrollTo(l, t)
        }
        if (mView4 != null) {
            mView4!!.scrollTo(l, t)
        }
    }

    /**
     * 设置跟它联动的view
     *
     * @param
     */

    fun setScrollView(view: View) {
        mView = view
    }

    fun setScrollView(view: View, view1: View, view2: View) {
        mView = view
        mView1 = view1
        mView2 = view2

    }

    fun setScrollView(view: View, view1: View, view2: View, view3: View) {
        mView = view
        mView1 = view1
        mView2 = view2
        mView3 = view3
    }

    fun setScrollView(view: View, view1: View, view2: View, view3: View, view4: View) {
        mView = view
        mView1 = view1
        mView2 = view2
        mView3 = view3
        mView4 = view4
    }
}
