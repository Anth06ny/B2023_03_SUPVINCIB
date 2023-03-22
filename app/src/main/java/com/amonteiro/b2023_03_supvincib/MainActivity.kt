package com.amonteiro.b2023_03_supvincib

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.amonteiro.b2023_03_supvincib.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    //creation des composants graphiques
    //lazy action a retardement à la 1ere utilisation
    val binding by lazy {   ActivityMainBinding.inflate(layoutInflater)}

    //Création de l'écran
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Affiche une IHM
        setContentView(binding.root)

        binding.btValidate.setOnClickListener {
            binding.et.setText("Clic sur valider")
        }

        binding.btCancel.setOnClickListener {
            binding.et.setText("Clic sur annuler")
        }

    }
}