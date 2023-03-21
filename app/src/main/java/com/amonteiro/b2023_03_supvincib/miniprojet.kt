package com.amonteiro.b2023_03_supvincib

fun main() {
    //Lazy loading + une seule instanciation
    var html: String = RequestUtils.sendGet("https://www.google.fr")
    println(html)
}