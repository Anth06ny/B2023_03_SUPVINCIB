package com.amonteiro.b2023_03_supvincib

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

//Utilisation

const val API_WEATHER = "https://api.openweathermap.org/data/2.5/weather?q=##1&appid=b80967f0a6bd10d23e44848547b26550&units=metric&lang=fr"

object RequestUtils {

    val client = OkHttpClient()
    val gson = Gson()

    fun loadWeather(cityName: String): WeatherBean {
        val json = sendGet(API_WEATHER.replace("##1", cityName))
        Thread.sleep(5000)
        val data: WeatherBean = gson.fromJson(json, WeatherBean::class.java)
        return data
    }

    //Méthode qui prend en entrée une url, execute la requête
    //Retourne le code HTML/JSON reçu
    fun sendGet(url: String): String {
        println("url : $url")
        //Création de la requête
        val request = Request.Builder().url(url).build()
        //Execution de la requête
        return client.newCall(request).execute().use { //it:Response
            //use permet de fermer la réponse qu'il y ait ou non une exception
            //Analyse du code retour
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}")
            }
            //Résultat de la requête
            it.body.string()
        }
    }
}

