package com.amonteiro.b2023_03_supvincib

import java.util.*


fun main() {
//    var car = CarBean("Seat", "Leon")
//    car.color = "grise"
//
//    println(car)
//    println("${car.marque} ${car.model} ${car.color}")
//
//    var student = StudentBean("Toto")
//    student.note++
//    println("${student.name} ${student.note}")
//
//    PrintRandomIntBean()

    val plane = PlaneBean("toto")
    val plane2 = PlaneBean("toto")

}

class PlaneBean(name:String){
    var id = name.hashCode()
        private set

    var name = name
        set(value) {
            field = value
            id = name.hashCode()
        }
}


data class UserBean(val name:String, var note: Int = 0){
    val id = name.hashCode()
}
data class TotoBean(val name:String, var note: Int = 0){
    val id = name.hashCode()
}

class PrintRandomIntBean(val max :Int){
    private val random = Random()

    init {
        println("init")
        println(random.nextInt(max))
        println(random.nextInt(max))
        println(random.nextInt(max))
    }

    constructor() : this(100) {
        println("constructor")
        println(random.nextInt(max))
    }
}

data class StudentBean(val name:String) {
    var note = 0
}

data class CarBean(var marque:String, var model:String) {
    var color : String? = null
}