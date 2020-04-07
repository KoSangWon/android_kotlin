package com.example.namecard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    fun init(){
        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked)
                textView2.setText("제 나이는 26살입니다. \n현재 건국대학교에 재학중입니다. \n저는 경기도 용인에 살고 있습니다.")
            else
                textView2.setText("")
        }
    }

}
