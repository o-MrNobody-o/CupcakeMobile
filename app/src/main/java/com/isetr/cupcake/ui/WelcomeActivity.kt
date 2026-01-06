package com.isetr.cupcake.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.isetr.cupcake.R
import com.isetr.cupcake.databinding.ActivityWelcomeBinding
import com.isetr.cupcake.ui.account.AccountActivity
import com.isetr.cupcake.ui.auth.AuthActivity
import com.isetr.cupcake.ui.products.PastryProductsActivity

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome)

        // Get first and last name from Intent extras
        val nom = intent.getStringExtra("EXTRA_NOM") ?: ""
        val prenom = intent.getStringExtra("EXTRA_PRENOM") ?: ""

        binding.userName = "$prenom $nom"

        // 🍰 Pastry products (empty page for now)
        binding.btnProducts.setOnClickListener {
            startActivity(Intent(this, PastryProductsActivity::class.java))
        }

        // 👤 Account info
        binding.btnAccount.setOnClickListener {
            startActivity(Intent(this, AccountActivity::class.java))
        }

        // 🚪 Logout
        binding.btnLogout.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}