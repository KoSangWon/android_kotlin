package com.example.mediaplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mediaPlayer: MediaPlayer?=null
    var vol = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    fun init(){
        imageView.setVolumeListener(object:VolumeControlView.VolumeListener{
            override fun onChanged(angle: Float) {
                vol = if(angle > 0){
                    angle/360
                }else{
                    (360+angle)/360
                }
                mediaPlayer?.setVolume(vol, vol)
            }
        })

        startBtn.setOnClickListener {
            if(mediaPlayer==null){
                mediaPlayer = MediaPlayer.create(this, R.raw.sample)
                mediaPlayer?.setVolume(vol, vol)
            }
            mediaPlayer?.start()
        }

        pauseBtn.setOnClickListener {
            mediaPlayer?.pause()
        }

        stopBtn.setOnClickListener {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }
}
