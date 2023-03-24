package com.amonteiro.b2023_03_supvincib

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.amonteiro.b2023_03_supvincib.databinding.ActivityPlaneteInfoBinding

class PlaneteInfoActivity : AppCompatActivity() {

    //IHM
    val binding by lazy { ActivityPlaneteInfoBinding.inflate(layoutInflater) }

    //C'est le framework qui se charge de la création ou récupération
    val model by lazy { ViewModelProvider(this).get(PlaneteInfoViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            model.loadData(binding.et.text.toString())
        }

        observe()
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
            if(it != null) {
                binding.tvResponse.setText("La planete ${it.name} a une periode de  ${it.period} et une température de ${it.temperature}° ")
            }
            else{
                binding.tvResponse.setText("-")
            }

        }
    }



}