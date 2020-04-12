package com.example.textinputedittext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    fun init(){
        emailText.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if(s.toString().contains("@")){
                    textInputLayout1.error=null
                }
                else{
                    textInputLayout1.error="이메일 형식이 올바르지 않습니다."
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }
}
