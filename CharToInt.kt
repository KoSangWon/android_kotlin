package com.example.myfirstapp

import java.lang.IllegalArgumentException

fun decimalDigitValue(c:Char):Int{
    if(c !in '0'..'9')
        throw IllegalArgumentException("out of range")
    return c.toInt() - '0'.toInt()
}

fun main(){
    val data = '7'
    println(data.toInt())
    val num = decimalDigitValue(data)
    println(num*2)
}