package com.example.myapplication

fun main(){
    //유사 기능들(함수)의 집합체(객체)

    //1. class - 자동차(시동, 운전), 사람(밥먹는다, 물마신다, 걷는다), 동물(뛴다)
    //2. data class - set, get

    var cls = HelloClass()
    var cls2 = HelloClass(1)

    println(cls2.age)

    var person = Person(1, "Sangwon")

    println(person.age)
}

class HelloClass{
    var age: Int = 0
//    init{
//        this.age = age
//    }
    //def 생성자, 보조 생성자
    constructor() //기본 생성자 - 값을 넘기면서 초기화 불가능
    constructor(age: Int){ //set
        this.age = age
    } //보조 생성자 - 값을 넘기면서 초기화 가능
    //보조 생성자를 사용하게 되면 기본 생성자를 수동으로 코딩해야함(무조건 써줘야함)
}

//data class : set, get 동작을 간편하게 구현해줌
data class Person(var age: Int, val name: String)
