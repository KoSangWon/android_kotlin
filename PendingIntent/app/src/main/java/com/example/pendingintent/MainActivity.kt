package com.example.pendingintent

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.sql.Time
import java.util.*

class MainActivity : AppCompatActivity() {

    var mymemo = ""
    var myhour = 0
    var mymin = 0
    var message = ""

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val str = intent.getStringExtra("time")
        if(str!=null){
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
        }
        init()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun init(){
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val dialog = layoutInflater.inflate(R.layout.mytimepicker_dialog, null)
            val dlgTime = dialog.findViewById<TimePicker>(R.id.timePicker)
            val dlgMemo = dialog.findViewById<EditText>(R.id.editText)
            val dlgBuilder = AlertDialog.Builder(this)
            dlgBuilder.setView(dialog)
                .setPositiveButton("추가"){
                    _,_ ->
                    mymemo = dlgMemo.text.toString()
                    myhour = dlgTime.hour
                    mymin = dlgTime.minute
                    message = myhour.toString() + "시 " + mymin.toString() + "분 : " + mymemo
                    val timerTask = object:TimerTask(){
                        @RequiresApi(Build.VERSION_CODES.O)
                        override fun run() {
                            makeNotification()
                        }
                    }
                    val timer = Timer()
                    timer.schedule(timerTask, 2000)
                    Toast.makeText(this, "알림이 추가되었습니다.", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("취소"){
                    _,_ -> //수행하는 내용 없음

                }
                .show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun makeNotification(){
        val channelId = "MyChannel"
        val channelName = "TimeCheckChannel"
        val notificationChannel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
        notificationChannel.enableVibration(true)
        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.BLUE
        notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_stat_name)
            .setContentTitle("일정알림")
            .setContentText(message)
            .setAutoCancel(true)
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("time", message)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP

        val pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        builder.setContentIntent(pendingIntent)

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(notificationChannel)
        val notification = builder.build()
        manager.notify(10, notification)
    }
}
