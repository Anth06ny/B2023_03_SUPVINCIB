package com.amonteiro.b2023_03_supvincib

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

//Utilisation

const val API_WEATHER = "https://api.openweathermap.org/data/2.5/weather?q=##1&appid=b80967f0a6bd10d23e44848547b26550&units=metric&lang=fr"

fun main() {
    println(RequestUtils.planetInfo("mars"))

}

object RequestUtils {

    val client = OkHttpClient()
    val gson = Gson()

    fun planetsInfo(planeteName: String): List<PlaneteBean> {
        val request = Request.Builder()
            .url("https://planets-by-api-ninjas.p.rapidapi.com/v1/planets?name=$planeteName")
            .get()
            .addHeader("X-RapidAPI-Key", "93329c7cf9msha136bd696cd1040p10a1dejsnbc52cdb0746e")
            .addHeader("X-RapidAPI-Host", "planets-by-api-ninjas.p.rapidapi.com")
            .build()

        client.newCall(request).execute().use { //it:Response
            //use permet de fermer la réponse qu'il y ait ou non une exception
            //Analyse du code retour
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}")
            }
            //Résultat de la requête
            val json = it.body.string()
            val data = gson.fromJson(json, Array<PlaneteBean>::class.java)
            return data.toList()
        }
    }

    fun planetInfo(planeteName: String): PlaneteBean? {
        val request = Request.Builder()
            .url("https://planets-by-api-ninjas.p.rapidapi.com/v1/planets?name=$planeteName")
            .get()
            .addHeader("X-RapidAPI-Key", "93329c7cf9msha136bd696cd1040p10a1dejsnbc52cdb0746e")
            .addHeader("X-RapidAPI-Host", "planets-by-api-ninjas.p.rapidapi.com")
            .build()

        client.newCall(request).execute().use { //it:Response
            //use permet de fermer la réponse qu'il y ait ou non une exception
            //Analyse du code retour
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}")
            }
            //Résultat de la requête
            val json = it.body.string()
            val data = gson.fromJson(json, Array<PlaneteBean>::class.java)
            return data.firstOrNull()
        }
    }

    fun loadWeather(cityName: String): WeatherBean {
        val json = sendGet(API_WEATHER.replace("##1", cityName))
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

