package com.amonteiro.b2023_03_supvincib

fun main() {
////    Créer une variable v1 de type String et y mettre la chaine "toto"
//    var v1 = "toto"
//    //    Afficher dans la console sa version en majuscule (uppercase)
//    println(v1.uppercase())
////    Essayer d'y mettre la valeur null. (Erreur de compilation)
////    Créer une variable v2 de type String nullable et y mettre la chaine "toto"
//    var v2: String? = "toto"
////    Afficher dans la console sa version en majuscule. Obligé de mettre le safe check
//    println(v2?.uppercase())
//    v2 = null
//
////    Créer une variable v3 de type String nullable et y mettre la valeur null
//    var v3: String? = null
//    //    Afficher dans la console sa version en majuscule
//    println(v3?.uppercase())
//
//    var v4: String = v3 + v3
//    println(v4)
//
//    if (v3.isNullOrBlank()) {
//        println("v3 est null")
//    }

    var res = boulangerie(nbSand = 5)
    println("res=$res")

    val plane = PlaneBean("toto")
    println("${plane.name} :  ${plane.id}")
    plane.name = "tata"
    println("${plane.name} :  ${plane.id}")
    plane.name = "toto"
    println("${plane.name} :  ${plane.id}")

}

fun boulangerie(nbCroi : Int = 0, nbBag:Int = 0, nbSand:Int = 0) =
    nbCroi * PRICE_CROI + nbBag * PRICE_BAG + nbSand * PRICE_SAND



fun myPrint(chaine:String) = println("#$chaine#")

fun pair(c:Int) = c%2 == 0

fun min(a: Int, b: Int, c: Int) = if (a < b && a < c) a else if (b < a && b < c) b else c
