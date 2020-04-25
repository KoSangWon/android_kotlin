package com.example.vocaquiz

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
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

        //recyclerView 에서 1~5 랜덤 뜻 뽑음
        //그 뜻에 해당하는 영어단어 얻어냄
        //그 영어단어 띄워줌
        //그 클릭한게 영어단어랑 똑같으면 정답입니다. 점수 + 10
        //다르면 틀렸습니다.
//
//        var randomNum = Random().nextInt(5)
//        var a = adapter.exampleArray[randomNum]
//        var str = meaningArray[1]
//
//        var tempIdx = 0
//        for(i in 0 until meaningArray.size){
//            if(meaningArray[i] == str) {
//                tempIdx = i
//                break
//            }
//        }
//
//        english.text = a.toString()

        var randNum = Random().nextInt(englishArray.size)
        english.text = englishArray[randNum]
        //englishArray[adapter.exampleArray[randNum]] = meaningArray[randNum]

        adapter.itemClickListener = object:MyAdapter.OnItemClickListener{
            override fun OnItemClick(
                holder: MyAdapter.MyViewHolder,
                view: View,
                data: String,
                position: Int
            ) {

                //english.text = englishArray[adapter.exampleArray[3]]
                //englishArray[adapter.exampleArray[3]] = meaningArray[randNum]
                finish();
                startActivity(intent);
            }
        }

    }

    fun readFile(){
        val scan = Scanner(resources.openRawResource(R.raw.words))
        while(scan.hasNextLine()){
            val word = scan.nextLine()
            val meaning = scan.nextLine()
            words[word] = meaning
            englishArray.add(word)//영어가 담겨 있는 배열
            meaningArray.add(meaning)//뜻 들이 담겨 있는 배열
            //lineCnt++
        }
        scan.close()
    }

}
