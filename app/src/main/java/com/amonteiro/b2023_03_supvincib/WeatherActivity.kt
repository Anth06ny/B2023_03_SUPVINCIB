package com.amonteiro.b2023_03_supvincib

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.amonteiro.b2023_03_supvincib.databinding.ActivityWeatherBinding
import kotlin.concurrent.thread

class WeatherActivity : AppCompatActivity() {

    val binding by lazy {   ActivityWeatherBinding.inflate(layoutInflater)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btLoad.setOnClickListener {

            binding.tv.setText("Avant Thread")
            thread {
                var data = RequestUtils.loadWeather("Nice")
                Thread.sleep(5000)

                //Permet de revenir sur le thread Principale
                runOnUiThread {
                    binding.tv.setText("Il fait ${data.main.temp}° à ${data.name} avec un vent de ${data.wind.speed} km/h")
                }
            }
        }

    }

}