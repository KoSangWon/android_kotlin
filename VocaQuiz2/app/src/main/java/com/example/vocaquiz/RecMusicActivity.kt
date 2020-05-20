package com.example.vocaquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_rec_music.*

class RecMusicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rec_music)
        init()
    }

    private fun init() {
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.settings.builtInZoomControls = true
        webView.settings.defaultTextEncodingName = "utf-8"
        webView.loadUrl("https://www.youtube.com/watch?v=gJ5oCwVHBEw")
    }
}
