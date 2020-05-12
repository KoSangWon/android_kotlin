package com.example.webparsing

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.constraintlayout.solver.GoalRow
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.ref.WeakReference
import java.net.HttpCookie
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    fun init() {
        button.setOnClickListener {
            val url = URL(editText.text.toString())
            val myTask = MyAsyncTask(this)
            myTask.execute(url)
        }
    }

    class MyAsyncTask(context:MainActivity):AsyncTask<URL, Unit, String>() {
        val activityreference = WeakReference(context)
        override fun onPreExecute() {
            super.onPreExecute()
            val activity = activityreference.get()
            activity?.progressBar?.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg params: URL?): String {
            var result = ""
            val connect = params[0]?.openConnection() as HttpURLConnection
            connect.connectTimeout = 4000
            connect.readTimeout = 4000
            connect.requestMethod = "GET"
            connect.connect()
            val responseCode = connect.responseCode
            if (responseCode == 200) {
                result = streamToString(connect.inputStream)
            }
            return result
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            val activity = activityreference.get()
            if(activity==null || activity.isFinishing)
                return
            activity.textView.text = result
            activity.progressBar.visibility = View.GONE
        }

        fun streamToString(inputStream: InputStream): String {
            val bufferReader = BufferedReader(InputStreamReader(inputStream))
            var line: String
            var result = ""
            try {
                do {
                    line = bufferReader.readLine()
                    if (line != null) {
                        result += line
                    }
                } while (line != null)
                inputStream.close()
            } catch (ex: Exception) {
                Log.e("error", "읽기 실패")
            }
            return result
        }
    }
}
