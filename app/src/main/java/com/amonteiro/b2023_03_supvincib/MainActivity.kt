package com.amonteiro.b2023_03_supvincib

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

    //Callback de la création du menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menu.add(0,5,0,"Météo")
        menu.add(0,6,0,"DemoRapidAPI")
        menu.add(0,7,0,"Maps")

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == 5) {
            //Action faire
            val intent = Intent(this, WeatherActivity::class.java)
            //Lancer l'action à faire
            startActivity(intent)
        }

        if(item.itemId == 6) {
            //Action faire
            val intent = Intent(this, PlaneteInfoActivity::class.java)
            //Lancer l'action à faire
            startActivity(intent)
        }
 if(item.itemId == 7) {
            //Action faire
            val intent = Intent(this, MapsActivity::class.java)
            //Lancer l'action à faire
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }

}