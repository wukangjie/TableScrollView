package com.wukangjie.tablescrollview.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wukangjie.tablescrollview.R
import com.wukangjie.tablescrollview.common.CommonActivity
import com.wukangjie.tablescrollview.special.SpecialActivity

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.main_recycler)
        recyclerView.layoutManager = LinearLayoutManager(baseContext)
        val list = ArrayList<MainData>()
        list.add(MainData("普通版"))
        list.add(MainData("升级版"))
        val mainAdapter = MainAdapter(this, list)
        recyclerView.adapter = mainAdapter
        mainAdapter.onItemClick = object : MainAdapter.OnItemClick {
            override fun onItemClick(position: Int) {
                when (position) {
                    0 -> {
                        startActivity(Intent(baseContext, CommonActivity::class.java))
                    }
                    1 -> {
                        startActivity(Intent(baseContext, SpecialActivity::class.java))
                    }
                }
            }
        }
    }


}
