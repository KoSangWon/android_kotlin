package com.example.vocaquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var data: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        initRecyclerView()
    }

    private fun initRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = MyAdapter(data)
    }

    private fun initData(){
        data.add("a")
        data.add("a")
        data.add("a")
        data.add("a")
        data.add("a")
        data.add("a")
        data.add("a")
        data.add("a")
    }
}
