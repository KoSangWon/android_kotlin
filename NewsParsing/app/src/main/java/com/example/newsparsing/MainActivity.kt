package com.example.newsparsing

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
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var words = mutableMapOf<String, String>()
    var englishArray = ArrayList<String>()
    var meaningArray = ArrayList<String>()



    lateinit var adapter:MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        var score = intent.getIntExtra("SCORE", 0)
        var solvedCnt = intent.getIntExtra("SOLVED", 0)
        var correctCnt = intent.getIntExtra("CORRECT", 0)
        var tempArr = ArrayList<Int>()
        var flag = false

        scoreText.text = "점수 : $score"
        solved.text = "푼 문제 수 : $solvedCnt"
        correct.text = "맞은 문제 수 : $correctCnt"


        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapter = MyAdapter(meaningArray)
        recyclerView.adapter = adapter

        var randNum = Random().nextInt(englishArray.size)
        english.text = englishArray[randNum]
        adapter.exampleArray.add(randNum)

        adapter.itemClickListener = object:MyAdapter.OnItemClickListener{
            override fun OnItemClick(
                    holder: MyAdapter.MyViewHolder,
                    view: View,
                    data: String,
                    position: Int
            ) {
                for(i in 0 until englishArray.size){
                    if(english.text == englishArray[i]) {
                        tempArr.add(i)
                    }
                }

                for(i in 0 until tempArr.size){
                    if(holder.voca.text == meaningArray[tempArr[i]]) {
                        Toast.makeText(applicationContext, "정답입니다!", Toast.LENGTH_SHORT).show()
                        score += 10
                        correctCnt++
                        flag = true
                        break
                    }
                }
                if(!flag){
                    Toast.makeText(applicationContext, "오답입니다!", Toast.LENGTH_SHORT).show()
                }
                solvedCnt++

                intent.putExtra("SCORE", score)
                intent.putExtra("SOLVED", solvedCnt)
                intent.putExtra("CORRECT", correctCnt)
                finish()
                startActivity(intent)
            }
        }

    }

}