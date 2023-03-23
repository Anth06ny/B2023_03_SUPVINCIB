package com.amonteiro.b2023_03_supvincib

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.amonteiro.b2023_03_supvincib.databinding.ActivityWeatherBinding
import com.squareup.picasso.Picasso

class WeatherActivity : AppCompatActivity() {

    //IHM
    val binding by lazy { ActivityWeatherBinding.inflate(layoutInflater) }

    //C'est le framework qui se charge de la création ou récupération
    val model by lazy { ViewModelProvider(this).get(WeatherViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        observe()

        //Clic sur le bouton
        binding.btLoad.setOnClickListener {
            model.loadData(binding.etCityName.text.toString())
        }
    }

    fun observe() {

        model.runInProgress.observe(this){
            binding.progressBar.isVisible = it
        }

        //on observe la donnée et cela déclanche l'observer quand ca change et à la subscription
        model.errorMessage.observe(this) {
            //CAS ERREUR
            if (it.isNotBlank()) {
                binding.tvError.isVisible = true
                binding.tvError.setText(it)
            }
            else {
                binding.progressBar.isVisible = false
            }
        }

        model.data.observe(this) {
            //CAS QUI MARCHE
            //Mise à jour des composants graphiques avec mes données
            binding.tv.text = it?.name ?: "-"
            binding.tvWind.text = it?.wind?.speed?.toString() ?: "-"
            binding.tvTemp.text = "${it?.main?.temp ?: "-"} °"
            binding.tvMinMax.text = "(${it?.main?.temp_min ?: "-"}°/${it?.main?.temp_max ?: "-"}°)"

            if (it?.weather?.isNotEmpty() == true) {
                binding.tvDesc.text = it?.weather?.get(0)?.description ?: "-"
                Picasso.get().load("https://openweathermap.org/img/wn/${it?.weather?.get(0)?.icon}@4x.png").into(binding.ivTemp)
            }
            else {
                binding.tvDesc.text = "-"
                binding.ivTemp.setImageBitmap(null)
            }
        }
    }

}