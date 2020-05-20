package com.example.vocaquiz

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_music.*

/**
 * A simple [Fragment] subclass.
 */
class MusicFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_music, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        musicBtn.setOnClickListener {
            val i = Intent(activity, MyMusicActivity::class.java)
            startActivity(i)
        }

        recMusicBtn.setOnClickListener {
            val i = Intent(activity, RecMusicActivity::class.java)
            startActivity(i)
        }
    }
}
