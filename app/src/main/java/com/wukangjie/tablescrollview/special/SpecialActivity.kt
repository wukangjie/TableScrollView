package com.wukangjie.tablescrollview.special

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.wukangjie.tablescrollview.R
import java.util.*

class SpecialActivity : AppCompatActivity(), ContentAdapter.OnContentScrollListener {


    lateinit var tvLeftTitle: TextView

    lateinit var rvTabRight: RecyclerView

    lateinit var horScrollview: CustomHorizontalScrollView

    lateinit var llTopRoot: LinearLayout

    internal lateinit var recyclerContent: RecyclerView

    internal lateinit var swipeRefresh: SwipeRefreshLayout
    private val mEntities = ArrayList<Entity>()
    private val rightMoveDatas = ArrayList<String>()
    private val topTabs = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_special)
        tvLeftTitle = findViewById(R.id.tv_left_title)
        rvTabRight = findViewById(R.id.rv_tab_right)
        horScrollview = findViewById(R.id.hor_scrollview)
        llTopRoot = findViewById(R.id.ll_top_root)
        recyclerContent = findViewById(R.id.recycler_content)
        swipeRefresh = findViewById(R.id.swipe_refresh)
        //处理顶部标题部分
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL)
        rvTabRight.layoutManager = linearLayoutManager
        val topTabAdpater = TopTabAdpater(this)
        rvTabRight.adapter = topTabAdpater
        for (i in 0..4) {
            topTabs.add("标题$i")
        }
        topTabAdpater.setDatas(topTabs)
        //处理内容部分
        recyclerContent.layoutManager = LinearLayoutManager(this)
        recyclerContent.setHasFixedSize(true)
        val contentAdapter = ContentAdapter(this)
        recyclerContent.adapter = contentAdapter
        contentAdapter.setOnContentScrollListener(this)
        recyclerContent.postDelayed({
            for (i in 0..29) {
                val entity = Entity()
                entity.leftTitle = "腾讯控股$i"
                rightMoveDatas.clear()
                for (j in 0..4) {
                    rightMoveDatas.add("价格$j")
                }
                entity.rightDatas = rightMoveDatas
                mEntities.add(entity)
            }
            contentAdapter.setDatas(mEntities)
            Toast.makeText(this, "加载完毕,加载了30条数据", Toast.LENGTH_SHORT).show()
        }, 1500)


        //滚动RV时,同步所有横向位移的item
        recyclerContent.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(@NonNull recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val viewHolderCacheList = contentAdapter.viewHolderCacheList
                val size = viewHolderCacheList.size
                for (i in 0 until size) {
                    viewHolderCacheList.get(i).horItemScrollview?.scrollTo(contentAdapter.offestX, 0)
                }

            }
        })

        //同步顶部tab的横向scroll和内容页面的横向滚动
        //同步滚动顶部tab和内容
        horScrollview.setOnCustomScrollChangeListener(object :
            CustomHorizontalScrollView.OnCustomScrollChangeListener {
            override fun onCustomScrollChange(
                listener: CustomHorizontalScrollView,
                scrollX: Int,
                scrollY: Int,
                oldScrollX: Int,
                oldScrollY: Int
            ) {
                //代码重复,可以抽取/////
                val viewHolderCacheList = contentAdapter.viewHolderCacheList
                val size = viewHolderCacheList.size
                for (i in 0 until size) {
                    viewHolderCacheList.get(i).horItemScrollview?.scrollTo(scrollX, 0)
                }
            }

        })

        //下拉刷新
        swipeRefresh.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                recyclerContent.postDelayed(Runnable {
                    for (i in 0..49) {
                        val entity = Entity()
                        entity.leftTitle = "腾讯控股$i"
                        rightMoveDatas.clear()
                        for (j in 0..4) {
                            rightMoveDatas.add("价格$j")
                        }
                        entity.rightDatas = rightMoveDatas
                        mEntities.add(entity)
                    }
                    contentAdapter.setDatas(mEntities)
                    swipeRefresh.setRefreshing(false)
                    Toast.makeText(baseContext, "刷新完毕,刷新了50条数据", Toast.LENGTH_SHORT).show()
                }, 1500)
            }
        })


    }

    override fun onScroll(offestX: Int) {
        //处理单个item滚动时,顶部tab需要联动
        horScrollview.scrollTo(offestX, 0)
    }
}
