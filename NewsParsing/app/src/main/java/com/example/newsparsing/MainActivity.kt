package com.example.newsparsing

import android.annotation.SuppressLint
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.icu.lang.UCharacter
import android.net.Uri
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.row.view.*
import org.json.JSONObject
import org.jsoup.Jsoup
import org.jsoup.parser.Parser
import java.lang.ref.WeakReference
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
//        startTask()
//        startXMLTask()
        startJSONTask()
    }

//    fun startTask(){
//        val task = MyAsyncTask(this)
//        task.execute(URL("https://www.daum.net"))
//    }
//
//    fun startXMLTask(){
//        val task = MyAsyncTask(this)
//        task.execute(URL("http://fs.jtbc.joins.com//RSS/culture.xml"))
//    }

    fun startJSONTask(){
        val task = MyAsyncTask(this)
        task.execute(URL("http://54.180.80.25/temp.php"))
    }

    fun init() {

        swiperefresh.setOnRefreshListener {
            swiperefresh.isRefreshing = true
            //startXMLTask()
//            startTask()
            startJSONTask()
        }

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        adapter = MyAdapter(ArrayList<MyData>())
        adapter.itemClickListener = object:MyAdapter.OnItemClickListener{
            override fun OnItemClick(
                holder: MyAdapter.MyViewHolder,
                view: View,
                data: MyData,
                position: Int
            ) {
                val intent = Intent(ACTION_VIEW, Uri.parse(adapter.items[position].url))
                startActivity(intent)
            }
        }
        recyclerView.adapter = adapter
    }

    class MyAsyncTask(context: MainActivity):AsyncTask<URL, Unit, Unit>(){
        val activityreference = WeakReference(context)

        override fun doInBackground(vararg params: URL?): Unit {
            val activity = activityreference.get()
            activity?.adapter?.items?.clear()

            val doc = Jsoup.connect(params[0].toString()).ignoreContentType(true).get()
            Log.i("JSON : ", doc.text())
            val json = JSONObject(doc.text())
            val array = json.getJSONArray("result")
            val temperature = array.getJSONObject(0).getString("temperature")
//            val joke = json.getJSONObject("result")
//            val jokestr = joke.getString("temperature")

            Log.i("Temperature ", temperature)

//            XML 파싱 코드
//            val doc = Jsoup.connect(params[0].toString()).parser(Parser.xmlParser()).get()
//            val headlines = doc.select("item")
//            for(news in headlines){
//                activity?.adapter?.items?.add(MyData(news.select("title").text(), news.select("link").text()))
//            }

//            html 파싱 코드
//            val doc = Jsoup.connect(params[0].toString()).get()
//            val headlines = doc.select("ul.list_txt>li>a")
//            for(news in headlines){
//                activity?.adapter?.items?.add(MyData(news.text(), news.absUrl("href")))
//            }
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