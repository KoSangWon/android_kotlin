package com.example.vocabulary

import android.content.Intent
import android.content.Intent.ACTION_MAIN
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter = MyAdapter(ArrayList<MyData>())
        recyclerView.adapter = adapter
        val intent = Intent(ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        val applist = packageManager.queryIntentActivities(intent, 0)
        if(applist.size > 0){
            for(appinfo in applist){
                val myapplabel = appinfo.loadLabel(packageManager)
                val myappclass = appinfo.activityInfo.name
                val myapppackname = appinfo.activityInfo.packageName
                val myappicon = appinfo.loadIcon(packageManager)
                adapter.items.add(MyData(myapplabel.toString(), myappclass, myapppackname, myappicon))
            }
        }
        adapter.itemClickListener = object : MyAdapter.OnItemClickListener{
            override fun OnItemClick(
                holder: MyAdapter.MyViewHolder,
                view: View,
                data: MyData,
                position: Int
            ) {
                val intent = packageManager.getLaunchIntentForPackage(adapter.items[position].myapppack)
                startActivity(intent)
            }
        }
    }
}