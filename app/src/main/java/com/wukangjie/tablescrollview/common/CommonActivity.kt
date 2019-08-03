package com.wukangjie.tablescrollview.common

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wukangjie.tablescrollview.R
import java.util.*

class CommonActivity : AppCompatActivity() {

    internal lateinit var mLeftListView: RecyclerView
    internal lateinit var mRightListView: RecyclerView
    //横竖联动头容器
    internal lateinit var mTitleHorScv: SyncHorizontalScrollView
    //横竖联动内容容器
    internal lateinit var mContentHorScv: SyncHorizontalScrollView
    internal lateinit var mTabAdapter: TabAdapter
    internal var mTabContentAdapter: TabAdapter? = null
    internal lateinit var list: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)
        initView()
        listData()
        initData()
    }

    private fun listData() {
        list.add("0")
        list.add("0")
        list.add("0")
        list.add("0")
        list.add("0")
        list.add("0")
        list.add("0")
        list.add("0")
        list.add("0")
        list.add("0")
        list.add("0")
        list.add("0")
        list.add("0")
        list.add("0")
    }

    private fun initData() {
        // 设置两个水平控件的联动
        mTitleHorScv.setScrollView(mContentHorScv)
        mContentHorScv.setScrollView(mTitleHorScv)
        mTabAdapter = TabAdapter(this, list, 0)
        mTabContentAdapter = TabAdapter(this, list, 1)
        mLeftListView.adapter = mTabAdapter
        mRightListView.adapter = mTabContentAdapter
        mLeftListView.layoutManager = LinearLayoutManager(this)
        mRightListView.layoutManager = LinearLayoutManager(this)
        mLeftListView.isNestedScrollingEnabled = false
        mRightListView.isNestedScrollingEnabled = false
    }

    private fun initView() {
        list = ArrayList()
        mLeftListView = findViewById(R.id.left_container_listview)
        mRightListView = findViewById(R.id.right_container_listview)
        mTitleHorScv = findViewById(R.id.title_horsv)
        mContentHorScv = findViewById(R.id.content_horsv)
    }
}
