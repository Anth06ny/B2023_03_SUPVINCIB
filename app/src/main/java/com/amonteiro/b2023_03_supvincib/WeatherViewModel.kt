package com.amonteiro.b2023_03_supvincib

import androidx.lifecycle.ViewModel

class WeatherViewModel : ViewModel() {

    var data: WeatherBean? = null
    var errorMessage = ""


    fun loadData(cityName: String) {
        data = null
        errorMessage = ""

        try {
            //Requête en dehors de l'UIThread
            data = RequestUtils.loadWeather(cityName)
        }
        catch (e: Exception) {
            e.printStackTrace()
            errorMessage = e.message ?: "Une erreur est survenue"
        }
    }
}