package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var data:ArrayList<MyData> = ArrayList<MyData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        initRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu1, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuitem1 -> {
                recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            }
            R.id.menuitem2 -> {
                recyclerView.layoutManager = GridLayoutManager(this, 3)
            }
            R.id.menuitem3 -> {
                recyclerView.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this,
        LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = MyAdapter(data)
    }

    private fun initData(){
        data.add(MyData("item1", 10))
        data.add(MyData("item2", 15))
        data.add(MyData("item3", 15))
        data.add(MyData("item4", 10))
        data.add(MyData("item5", 20))
        data.add(MyData("item6", 15))
        data.add(MyData("item7", 10))
        data.add(MyData("item8", 20))
        data.add(MyData("item9", 15))
        data.add(MyData("item10", 20))
    }
}
