package com.example.myfragmentapp

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.fragment_image.*

/**
 * A simple [Fragment] subclass.
 */
class ImageFragment : Fragment() {
    var imgNum = 1 //image번호
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    fun checkOrientation(){
        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            val txtFrag = requireActivity().textFragment as TextFragment
            txtFrag.setActiveImage(imgNum)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        imageView.setOnClickListener {
            if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
                val i = Intent(activity, SecondActivity::class.java)//from, to
                i.putExtra("imgNum", imgNum)
                startActivity(i)
            }else if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
                val txtFrag = requireActivity().textFragment as TextFragment
                txtFrag.setActiveImage(imgNum)
            }
        }

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.radioBtn1 -> {
                    imageView.setImageResource(R.drawable.img1)
                    imgNum = 1
                }
                R.id.radioBtn2 -> {
                    imageView.setImageResource(R.drawable.img2)
                    imgNum = 2
                }
                R.id.radioBtn3 -> {
                    imageView.setImageResource(R.drawable.img3)
                    imgNum = 3
                }
            }
            checkOrientation()
        }
    }
}
