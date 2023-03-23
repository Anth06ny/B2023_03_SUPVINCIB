package com.amonteiro.b2023_03_supvincib

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.concurrent.thread

class WeatherViewModel : ViewModel() {

    var data = MutableLiveData<WeatherBean?>(null)
    var errorMessage = MutableLiveData("")
    var runInProgress = MutableLiveData(false)

    fun loadData(cityName: String) {
        //PostValue : Met à jour la donnée et déclanche l'observer sur l'UIThread
        data.postValue(null)
        errorMessage.postValue("")
        runInProgress.postValue(true)

        //Lancement d'une tâche asynchrone (thread separé)
        thread {
            try {
                //Requête en dehors de l'UIThread
                data.postValue(RequestUtils.loadWeather(cityName))
            }
            catch (e: Exception) {
                e.printStackTrace()
                errorMessage.postValue(e.message ?: "Une erreur est survenue")
            }
            runInProgress.postValue(false)
        }
    }
}