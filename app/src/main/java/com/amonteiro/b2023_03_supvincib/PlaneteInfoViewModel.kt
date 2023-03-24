package com.amonteiro.b2023_03_supvincib

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.concurrent.thread

class PlaneteInfoViewModel : ViewModel() {

    var data = MutableLiveData<PlaneteBean?>(null)
    var errorMessage = MutableLiveData("")
    var runInProgress = MutableLiveData(false)

    fun loadData(planeteName: String) {
        //PostValue : Met à jour la donnée et déclanche l'observer sur l'UIThread
        data.postValue(null)
        errorMessage.postValue("")
        runInProgress.postValue(true)

        //Lancement d'une tâche asynchrone (thread separé)
        thread {
            try {
                //Controle
                if(planeteName.trim().length < 3) {
                    throw Exception("Il faut au moins 3 caractères")
                }

                //Requête en dehors de l'UIThread
                data.postValue(RequestUtils.planetInfo(planeteName))
            }
            catch (e: Exception) {
                e.printStackTrace()
                errorMessage.postValue(e.message ?: "Une erreur est survenue")
            }
            runInProgress.postValue(false)
        }
    }
}