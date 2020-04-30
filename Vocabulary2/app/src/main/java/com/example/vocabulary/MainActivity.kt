package com.example.vocabulary

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.textView2
import kotlinx.android.synthetic.main.row.*
import kotlinx.android.synthetic.main.row.view.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var words = mutableMapOf<String, String>()
    var array = ArrayList<String>()
    lateinit var adapter: MyAdapter
    lateinit var tts:TextToSpeech
    var isTtsReady = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    override fun onStop() {
        super.onStop()
        tts.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        tts.shutdown()
    }

    private fun init() {
        tts = TextToSpeech(this, TextToSpeech.OnInitListener {
            isTtsReady = true
            tts.language = Locale.US
        })
        readFile()
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = MyAdapter(array)

        adapter.itemClickListener = object:MyAdapter.OnItemClickListener{
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun OnItemClick(
                holder: MyAdapter.MyViewHolder,
                view: View,
                data: String,
                position: Int
            ) {
                if(isTtsReady)
                    tts.speak(data, TextToSpeech.QUEUE_ADD, null, null)
                //Toast.makeText(applicationContext, words[data], Toast.LENGTH_SHORT).show()

                view.textView3.text = words[data]
                if(view.textView3.visibility == View.GONE) {

                    view.textView3.visibility = View.VISIBLE;
                }
                else {
                    view.textView3.visibility = View.GONE;
                }
            }
        }


        recyclerView.adapter = adapter
        val simpleCallback = object :ItemTouchHelper.SimpleCallback(ItemTouchHelper.DOWN or ItemTouchHelper.UP, ItemTouchHelper.LEFT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                //TODO("Not yet implemented")
                adapter.moveItem(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                //TODO("Not yet implemented")
                adapter.removeItem(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    fun readFileScan(scan: Scanner){
        while(scan.hasNextLine()){
            val word = scan.nextLine()
            val meaning = scan.nextLine()
            words[word] = meaning
            array.add(word)
        }
        scan.close()
    }

    fun readFile(){

        val scan2 = Scanner(openFileInput("out.txt"))
        readFileScan(scan2)

        val scan = Scanner(resources.openRawResource(R.raw.words))
        readFileScan(scan)
    }

}