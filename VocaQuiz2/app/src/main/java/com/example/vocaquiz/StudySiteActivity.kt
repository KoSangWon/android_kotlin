package com.example.vocaquiz

import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_study_site.*
import org.json.JSONObject
import org.jsoup.Jsoup
import java.lang.ref.WeakReference
import java.net.URL
import java.util.ArrayList

class StudySiteActivity : AppCompatActivity() {

    lateinit var adapter: StudySiteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study_site)
        init()
        startTask()
    }

    fun startTask(){
        val task = MyAsyncTask(this)
        task.execute(URL("https://m.blog.naver.com/PostView.nhn?blogId=51tops&logNo=220231365331&proxyReferer=https:%2F%2Fwww.google.com%2F"))
    }

    fun init(){

        swiperefresh.setOnRefreshListener {
            swiperefresh.isRefreshing = true
            startTask()
        }

        siteRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        siteRecyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        adapter = StudySiteAdapter(ArrayList<MySiteData>())
        adapter.itemClickListener = object:StudySiteAdapter.OnItemClickListener{
            override fun OnItemClick(
                holder: StudySiteAdapter.MyViewHolder,
                view: View,
                data: MySiteData,
                position: Int
            ) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(adapter.items[position].url))
                startActivity(intent)
            }
        }
        siteRecyclerView.adapter = adapter
    }

    class MyAsyncTask(context: StudySiteActivity): AsyncTask<URL, Unit, Unit>(){
        val activityreference = WeakReference(context)

        override fun doInBackground(vararg params: URL?): Unit {
            val activity = activityreference.get()
            activity?.adapter?.items?.clear()

            val doc = Jsoup.connect(params[0].toString()).get()
            val titles = doc.select("div.post_ct>p>a")
            for(title in titles){
                activity?.adapter?.items?.add(MySiteData(title.text(), title.absUrl("href")))
            }
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            val activity = activityreference.get()
            if(activity==null || activity.isFinishing){
                return
            }
            activity.swiperefresh.isRefreshing = false
            activity.adapter.notifyDataSetChanged()
        }
    }
}
