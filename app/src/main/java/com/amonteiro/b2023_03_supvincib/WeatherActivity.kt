package com.amonteiro.b2023_03_supvincib

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.amonteiro.b2023_03_supvincib.databinding.ActivityWeatherBinding
import com.squareup.picasso.Picasso
import kotlin.concurrent.thread

class WeatherActivity : AppCompatActivity() {

    //IHM
    val binding by lazy { ActivityWeatherBinding.inflate(layoutInflater) }

    //C'est le framework qui se charge de la création ou récupération
    val model by lazy { ViewModelProvider(this).get(WeatherViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Clic sur le bouton
        binding.btLoad.setOnClickListener {

            //etCityName c'est un composant graphique on récupère avant le thread
            val cityName = binding.etCityName.text.toString()
            binding.progressBar.isVisible = true

            //Lancement d'une tâche asynchrone (thread separé)
            thread {
                model.loadData(cityName)
                //Permet de revenir sur le thread Principale
                runOnUiThread {
                    refreshScreen()
                    binding.progressBar.isVisible = false
                }
            }

        }

        refreshScreen()
    }

    fun refreshScreen() {
        //CAS QUI MARCHE
        //Mise à jour des composants graphiques avec mes données
        binding.tv.setText(model.data?.name ?: "-")
        binding.tvWind.setText(model.data?.wind?.speed?.toString() ?: "-")
        binding.tvTemp.setText("${model.data?.main?.temp ?: "-"} °")
        binding.tvMinMax.text = "(${model?.data?.main?.temp_min ?: "-"}°/${model.data?.main?.temp_max ?: "-"}°)"

        if (model.data?.weather?.isNotEmpty() == true) {
            binding.tvDesc.setText(model?.data?.weather?.get(0)?.description ?: "-")
            Picasso.get().load("https://openweathermap.org/img/wn/${model?.data?.weather?.get(0)?.icon}@4x.png").into(binding.ivTemp)
        }
        else {
            binding.tvDesc.setText("-")
            binding.ivTemp.setImageBitmap(null)
        }


        //CAS ERREUR
        if (model.errorMessage.isNotBlank()) {
            binding.tvError.isVisible = true
            binding.tvError.setText(model.errorMessage)
        }
        else {
            binding.progressBar.isVisible = false
        }


    }
}