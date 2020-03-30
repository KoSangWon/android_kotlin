package com.example.myapplication

fun main(){
    //val -> final, 초기화를 해주어서 타입을 추론해야 할 수 있어서 String 생략가능
    val hello1: String = "hello world"
    //var -> int, string, float, double
    var hello2: String = "hello world2"
    var hello3: String? = "hello world3"// ?가 있으면 null가능
    //hello1 = "123" //상수는 값을 재할당할 수 없다.

    println(hello1)
    println(hello2)
    println(hello())

}

fun hello(): String{ //void함수
    return "hello fun!"
}

