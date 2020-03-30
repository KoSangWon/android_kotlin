package com.example.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {//actictiy가 생성되면 호출되는 함수
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    fun init(){
        button.setOnClickListener {
            val bmi:Double = weight.text.toString().toInt() / (height.text.toString().toInt()/100.0).pow(2.0)
            var result = ""
            when{
                bmi >= 35 -> {
                    result = "고도비만"
                    imageView.setImageResource(R.drawable.ic_sentiment_dissatisfied_black_24dp)
                }
                bmi >= 23 -> {
                    result = "과체중"
                    imageView.setImageResource(R.drawable.ic_sentiment_neutral_black_24dp)
                }
                bmi >= 18.5 -> {
                    result = "정상"
                    imageView.setImageResource(R.drawable.ic_sentiment_satisfied_black_24dp)
                }
                else -> {
                    result = "저체중"
                    imageView.setImageResource(R.drawable.ic_sentiment_neutral_black_24dp)
                }
            }

            Toast.makeText(this, result, Toast.LENGTH_SHORT).show()

        }
    }
}
