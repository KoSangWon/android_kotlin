package com.example.sqlitedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var myDBHelper:MyDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    fun init(){
        myDBHelper = MyDBHelper(this)
        insertbtn.setOnClickListener {
            val quantity = pQuantityEdit.text.toString().toInt()
            val name = pNameEdit.text.toString()
            val product = Product(0, name, quantity)
            val result = myDBHelper.insertProduct(product)
            if(result){
                Toast.makeText(this, "DB INSERT SUCCESS", Toast.LENGTH_SHORT).show()
                getAllRecord()
            }else{
                Toast.makeText(this, "DB INSERT FAILED", Toast.LENGTH_SHORT).show()
            }
        }

        deletebtn.setOnClickListener {

        }

        updatebtn.setOnClickListener {

        }

        findbtn.setOnClickListener {

        }
    }

    fun getAllRecord(){
        myDBHelper.getAllRecord()
    }
}
