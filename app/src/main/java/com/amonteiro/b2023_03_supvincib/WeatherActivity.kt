package com.amonteiro.b2023_03_supvincib

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.amonteiro.b2023_03_supvincib.databinding.ActivityWeatherBinding
import kotlin.concurrent.thread

class WeatherActivity : AppCompatActivity() {

    //IHM
    val binding by lazy { ActivityWeatherBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(binding.root)
        setContentView(R.layout.activity_weather)

        Toast.makeText(this, "coucou", Toast.LENGTH_LONG).show()

        //Clic sur le bouton
        binding.btLoad.setOnClickListener {

            binding.tv.setText("Avant Thread")
            //Lancement d'une tâche asynchrone (thread separé)
            thread {
                //Requête en dehors de l'UIThread
                var data = RequestUtils.loadWeather("Nice")

                //Permet de revenir sur le thread Principale
                runOnUiThread {
                    //Mise à jour des composants graphiques avec mes données
                    binding.tv.setText("Il fait ${data.main.temp}° à ${data.name} avec un vent de ${data.wind.speed} km/h")
                }
            }
        }
    }
}