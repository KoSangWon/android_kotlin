package com.example.vocaquiz

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_study.*

/**
 * A simple [Fragment] subclass.
 */
class StudyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_study, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        quizBtn.setOnClickListener {
            val i = Intent(activity, IntroActivity::class.java)
            startActivity(i)
        }

        studyBtn.setOnClickListener {
            val i = Intent(activity, StudySiteActivity::class.java)
            startActivity(i)
        }

    }
}
