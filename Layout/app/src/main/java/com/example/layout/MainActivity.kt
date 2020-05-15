package com.example.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val checkbox = mutableListOf<CheckBox>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    fun init(){
        checkbox.add(checkBox1)//checkbox.add(findViewById(R.id.checkBox1))해도 됨
        checkbox.add(checkBox2)
        checkbox.add(checkBox3)
        checkbox.add(checkBox4)
        checkbox.add(checkBox5)
        checkbox.add(checkBox6)
        checkbox.add(checkBox7)
        checkbox.add(checkBox8)
        checkbox.add(checkBox9)
        checkbox.add(checkBox10)

        for(check in checkbox){
            check.setOnCheckedChangeListener { buttonView, isChecked ->
                val imgID = resources.getIdentifier(buttonView.text.toString(), "id", packageName)
                val imgView = findViewById<ImageView>(imgID)

                if(isChecked)
                    imgView.visibility = View.VISIBLE
                else
                    imgView.visibility = View.INVISIBLE
            }
        }
        }
}
