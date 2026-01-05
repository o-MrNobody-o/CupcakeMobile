package com.isetr.cupcake.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.isetr.cupcake.R
import com.isetr.cupcake.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome)

        // Get first and last name from Intent extras
        val nom = intent.getStringExtra("EXTRA_NOM") ?: ""
        val prenom = intent.getStringExtra("EXTRA_PRENOM") ?: ""

        binding.userName = "$prenom $nom"
    }
}
