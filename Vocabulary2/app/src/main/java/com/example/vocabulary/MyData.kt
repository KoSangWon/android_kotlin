package com.example.vocabulary

import java.io.Serializable

data class MyData(var word:String, var meaning:String, var check:Boolean=false):Serializable{
}