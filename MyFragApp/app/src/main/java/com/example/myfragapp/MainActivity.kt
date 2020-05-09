package com.example.myfragapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CoffeeFragment.OnListFragmentInteractionListener {

    val textArray = arrayListOf<String>("이미지", "리스트")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        viewPager.adapter = MyFragStateAdapter(this)
        TabLayoutMediator(tabLayout, viewPager){
            tab, position ->
            tab.text = textArray[position]
        }.attach()
    }

    override fun onListFragmentInteraction(item: String?) {
        Toast.makeText(this, item, Toast.LENGTH_SHORT).show()
    }
}
