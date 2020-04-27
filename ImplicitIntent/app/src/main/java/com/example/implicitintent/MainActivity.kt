package com.example.implicitintent

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val CALL_REQUEST = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    fun callAlertDlg(){
        val builder = AlertDialog.Builder(this)
        builder.setMessage("반드시 CALL_PHONE 권한이 허용되어야 합니다.")
            .setTitle("권한 허용")
        builder.setPositiveButton("OK"){
            _,_ -> ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), CALL_REQUEST)
        }
        val dlg = builder.create()
        dlg.show()
    }

    fun callAction(){
        val number = Uri.parse("tel:010-3477-8476")
        val callIntent = Intent(Intent.ACTION_CALL, number)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            callAlertDlg()
            //ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), CALL_REQUEST)
            //return
        }
        else {
            startActivity(callIntent)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            CALL_REQUEST -> {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "권한이 승인되었습니다.", Toast.LENGTH_SHORT).show()
                    callAction()//다시 실행
                }
                else{
                    Toast.makeText(this, "권한 승인이 거부되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun init(){
        callbtn.setOnClickListener {
            callAction()
        }

        msgbtn.setOnClickListener {
            val message = Uri.parse("sms:010-3477-8476")
            val messageIntent = Intent(Intent.ACTION_SENDTO, message)
            messageIntent.putExtra("sms_body", "빨리 다음꺼 하자..")
            startActivity(messageIntent)
        }

        webbtn.setOnClickListener {
            val webpage = Uri.parse("http://www.naver.com")
            val webIntent = Intent(Intent.ACTION_VIEW, webpage)
            startActivity(webIntent)
        }

        mapbtn.setOnClickListener {
            val location = Uri.parse("geo:37.543684,127.077130?z=16")
            val mapIntent = Intent(Intent.ACTION_VIEW, location)
            startActivity(mapIntent)
        }
    }
}
