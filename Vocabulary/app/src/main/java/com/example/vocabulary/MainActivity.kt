package com.example.vocabulary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var words = mutableMapOf<String, String>()
    var array = ArrayList<String>()
    lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        readFile()
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = MyAdapter(array)
        recyclerView.adapter = adapter
    }

    fun readFile(){
        val scan = Scanner(resources.openRawResource(R.raw.words))
        while(scan.hasNextLine()){
            val word = scan.nextLine()
            val meaning = scan.nextLine()
            words[word] = meaning
            array.add(word)
        }
        scan.close()
    }

}
