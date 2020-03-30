package com.example.myapplication

fun main(){
    //map - key, value pair // JSON // name! sangwon(value)
    //{"name","sangwon"}

    //수정 불가능
    var map1 = mapOf(Pair("name","sangwon"))//큰따옴표를 넣었기때문에 이미 타입이 정해진것임

    //수정 가능
    var map2 = mutableMapOf<String, String>() //type
    map2.put("name","sangtwo")
    map2.put("age","20")

    //keys
    println(map2.keys)

    for(map in map2) {
        println(map)
        println(map.value)
    }
}
