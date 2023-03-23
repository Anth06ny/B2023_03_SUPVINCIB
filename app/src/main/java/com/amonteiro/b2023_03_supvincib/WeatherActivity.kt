package com.amonteiro.b2023_03_supvincib

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.amonteiro.b2023_03_supvincib.databinding.ActivityWeatherBinding
import com.squareup.picasso.Picasso
import kotlin.concurrent.thread

class WeatherActivity : AppCompatActivity() {

    //IHM
    val binding by lazy { ActivityWeatherBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Clic sur le bouton
        binding.btLoad.setOnClickListener {

            //etCityName c'est un composant graphique on récupère avant le thread
            val cityName = binding.etCityName.text.toString()
            binding.progressBar.isVisible = true
            binding.tvError.isVisible = false

            //Lancement d'une tâche asynchrone (thread separé)

            thread {
                try {

                    //Requête en dehors de l'UIThread
                    var data = RequestUtils.loadWeather(cityName)

                    //Permet de revenir sur le thread Principale
                    runOnUiThread {
                        //Mise à jour des composants graphiques avec mes données
                        binding.tv.setText(data.name)
                        binding.tvWind.setText(data.wind.speed.toString())
                        binding.tvTemp.setText("${data.main.temp}°")
                        binding.tvMinMax.text = "(${data.main.temp_min}°/${data.main.temp_max}°)"

                        if (data.weather.isNotEmpty()) {
                            binding.tvDesc.setText(data.weather[0].description)
                            Picasso.get().load("https://openweathermap.org/img/wn/${data.weather[0].icon}@4x.png").into(binding.ivTemp)
                        }

                        binding.progressBar.isVisible = false
                    }
                }
                catch (e: Exception) {
                    e.printStackTrace()
                    runOnUiThread {
                        binding.progressBar.isVisible = false
                        binding.tvError.setText(e.message)
                        binding.tvError.isVisible = true
                    }
                }
            }

        }
    }
}