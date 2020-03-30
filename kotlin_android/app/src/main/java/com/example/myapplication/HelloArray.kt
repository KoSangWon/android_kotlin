package com.example.myapplication

fun main(){
    //1. 배열
    //2. loop 반복
    //3. 캐스팅

    //1. 배열 = {"","",""}
    var arr1 = listOf("1","2")
    var arr2 = mutableListOf("1","2")

    //2. 반복문 (향상된 반복문)
    for(item in arr1) {
        println(item)
    }
    for((index, item) in arr1.withIndex()){
        println("$index, $item")
    }

    //3. casting object < String, int, long .... // Any - auto casting
    var hello: Any = "hello"//Any : 너의 가문은 어디니?
    if(hello is String){//자동으로 이미 hello가 String으로 변환되어 있음.
        var str: String = hello
    }
}

