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

        println("coucou")
        var i = 0
        println(18/i)

        //Clic sur le bouton valider
        binding.btValidate.setOnClickListener {
            if(binding.rbLike.isChecked) {
                binding.et.setText(binding.rbLike.text)
            }
            else if(binding.rbDislike.isChecked) {
                binding.et.setText(binding.rbDislike.text)
            }
            else {
                binding.et.setText("")
            }

            binding.iv.setImageResource(R.drawable.baseline_delete_forever_24)
            binding.iv.setColorFilter(getColor(R.color.purple_500))
        }

        binding.btCancel.setOnClickListener {
            binding.et.setText("")
            //décoche tous les radiobutton
            binding.rg.clearCheck()
            binding.iv.setImageResource(R.drawable.baseline_flag_24)
        }
    }


}