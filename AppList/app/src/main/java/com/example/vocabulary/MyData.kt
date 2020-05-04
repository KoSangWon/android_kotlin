package com.example.vocabulary

import android.graphics.drawable.Drawable
import java.io.Serializable

data class MyData (var myapplabel:String, var myappclass:String, var myapppack:String, var myappicon:Drawable):Serializable{
}