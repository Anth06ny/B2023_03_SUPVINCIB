package com.amonteiro.b2023_03_supvincib


fun main() {
    exo4()
}

data class PersonBean(var name: String, var note: Int)

fun exo4() {
    val list = arrayListOf(
        PersonBean("toto", 16),
        PersonBean("Tata", 20),
        PersonBean("Toto", 8),
        PersonBean("Charles", 14)
    )

    println("Afficher la sous liste de personne ayant 10 et +")
    //println(list.filter { it.note >=10 })
    //Pour un affichage de notre choix
    println(list.filter { it.note >= 10 }.joinToString("\n") { "-${it.name} : ${it.note}" })

    val isToto : (PersonBean)->Boolean = {it.name.equals("toto", true)}
    //TODO
    println("\n\nAfficher combien il y a de Toto dans la classe ?")
    println(list.count(isToto))

    println("\n\nAfficher combien de Toto ayant la moyenne (10 et +)")
    println(list.count{ isToto(it) && it.note > 10})

    println("\n\nAfficher combien de Toto ont plus que la moyenne de la classe")
    val average = list.map { it.note }.average()
    println(list.count{ isToto(it) && it.note > average})

    println("\n\nAfficher la list triée par nom sans doublon")
    println("\n\nAjouter un point a ceux n’ayant pas la moyenne (<10)")
    println("\n\nAjouter un point à tous les Toto")
    println("\n\nRetirer de la liste ceux ayant la note la plus petite. (Il faut une ArrayList)")
    println("\n\nAfficher les noms de ceux ayant la moyenne(10et+) par ordre alphabétique")
}

fun exo1() {
    var lower: (String) -> Unit = { it: String -> println(it) }
    var lowerV2 = { it: String -> println(it) }
    var lowerv3: (String) -> Unit = { it -> println(it) }
    var lowerv4: (String) -> Unit = {
        println(it)
    }

    var v = lower("Toto")


}