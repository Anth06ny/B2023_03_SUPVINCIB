package com.amonteiro.b2023_03_supvincib

fun main() {
    //Lazy loading + une seule instanciation
//    var html: String = RequestUtils.sendGet("https://www.google.fr")
//    println(html)

    val weather= RequestUtils.loadWeather("Nice")
    println("Il fait ${weather.main.temp}° à ${weather.name} avec un vent de ${weather.wind.speed} km/h")

}