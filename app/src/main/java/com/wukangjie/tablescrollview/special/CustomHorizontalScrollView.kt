package com.wukangjie.tablescrollview.special

import android.content.Context
import android.util.AttributeSet
import android.widget.HorizontalScrollView


class CustomHorizontalScrollView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : HorizontalScrollView(context, attrs, defStyleAttr) {
    private var listener: OnCustomScrollChangeListener? = null

    interface OnCustomScrollChangeListener {
        fun onCustomScrollChange(
            listener: CustomHorizontalScrollView,
            scrollX: Int,
            scrollY: Int,
            oldScrollX: Int,
            oldScrollY: Int
        )
    }

    fun setOnCustomScrollChangeListener(listener: OnCustomScrollChangeListener) {
        this.listener = listener
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        if (null != listener)
            listener!!.onCustomScrollChange(this@CustomHorizontalScrollView, l, t, oldl, oldt)
    }
}
