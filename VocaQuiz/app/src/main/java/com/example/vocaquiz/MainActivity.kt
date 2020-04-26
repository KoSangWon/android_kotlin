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

    //var score = intent.getIntExtra("SCORE", 0)

    lateinit var adapter:MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        //scoreText.text = score.toString()
        readFile()
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
                if(words[english.text.toString()]?.equals(holder.voca.text)!!){
                    Toast.makeText(applicationContext, "정답입니다!", Toast.LENGTH_SHORT).show()
                    //score += 10
                }else{
                    Toast.makeText(applicationContext, "오답입니다!", Toast.LENGTH_SHORT).show()
                }
                //intent.putExtra("SCORE", score)
                finish()
                startActivity(intent)
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
        }
        scan.close()
    }

}
