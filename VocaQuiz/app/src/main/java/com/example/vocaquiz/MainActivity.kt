package com.example.vocaquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row.view.*
import java.util.*

class MainActivity : AppCompatActivity() {
    var words = mutableMapOf<String, String>()
    var englishArray = ArrayList<String>()
    var meaningArray = ArrayList<String>()
    var lineCnt = 0
    lateinit var answer:String
    lateinit var adapter:MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        readFile()
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)




        adapter = MyAdapter(meaningArray)
        recyclerView.adapter = adapter

        val random = Random()
        var num = random.nextInt(lineCnt)
        english.text = englishArray[num]
        answer = meaningArray[num]


        adapter.itemClickListener = object:MyAdapter.OnItemClickListener{
            override fun OnItemClick(
                holder: MyAdapter.MyViewHolder,
                view: View,
                data: String,
                position: Int
            ) {



            }
        }

    }

    fun showQuiz(){

    }


    fun readFile(){
        val scan = Scanner(resources.openRawResource(R.raw.words))
        while(scan.hasNextLine()){
            val word = scan.nextLine()
            val meaning = scan.nextLine()
            words[word] = meaning
            englishArray.add(word)//영어가 담겨 있는 배열
            meaningArray.add(meaning)//뜻 들이 담겨 있는 배열
            lineCnt++
        }
        scan.close()
    }

}
