package com.example.vocaquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_init.*

class InitActivity : AppCompatActivity() {
    val textArray = arrayListOf<String>("배경음악", "공부")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init)
        init()
    }

    private fun init(){
        viewPager.adapter = MyFragStateAdapter(this)
        TabLayoutMediator(tabLayout, viewPager){
                tab, position ->
            tab.text = textArray[position]
        }.attach()
    }
}
