package com.example.recyclerview

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var data: MutableList<String> = mutableListOf<String>()
        for (i in 9000..19999) {
            if (i % 4 != 0) {
                continue
            }
            data.add("可惜$i")
        }
        var recyclerView = findViewById<RecyclerView>(R.id.rv)
        recyclerView.layoutManager = StaggeredGridLayoutManager(3, LinearLayout.VERTICAL)
        val adpater = MyAdapter(this, data)
        recyclerView.adapter = adpater
    }
}