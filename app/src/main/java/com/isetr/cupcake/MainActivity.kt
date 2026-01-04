package com.isetr.cupcake

import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.isetr.cupcake.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup DataBinding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Login -> Register
        binding.tvGoRegister.setOnClickListener {
            binding.layoutLogin.animate().alpha(0f).setDuration(300).withEndAction {
                binding.layoutLogin.visibility = View.GONE
                binding.layoutRegister.alpha = 0f
                binding.layoutRegister.visibility = View.VISIBLE
                binding.layoutRegister.animate().alpha(1f).setDuration(300).start()
            }.start()
        }

        // Register -> Login
        binding.tvGoLogin.setOnClickListener {
            binding.layoutRegister.animate().alpha(0f).setDuration(300).withEndAction {
                binding.layoutRegister.visibility = View.GONE
                binding.layoutLogin.alpha = 0f
                binding.layoutLogin.visibility = View.VISIBLE
                binding.layoutLogin.animate().alpha(1f).setDuration(300).start()
            }.start()
        }

        // Show/hide password
        binding.cbShowPassword.setOnCheckedChangeListener { _, isChecked ->
            val type = if (isChecked)
                InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            else
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

            binding.etRegPassword.inputType = type
            binding.etRegConfirmPassword.inputType = type

            binding.etRegPassword.setSelection(binding.etRegPassword.text.length)
            binding.etRegConfirmPassword.setSelection(binding.etRegConfirmPassword.text.length)
        }

        // Login button
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            // TODO: Handle login logic
        }

        // Register button
        binding.btnRegister.setOnClickListener {
            val nom = binding.etRegNom.text.toString()
            val prenom = binding.etRegPrenom.text.toString()
            val email = binding.etRegEmail.text.toString()
            val adresse = binding.etRegAdresse.text.toString()
            val telephone = binding.etRegTelephone.text.toString()
            val password = binding.etRegPassword.text.toString()
            val confirmPassword = binding.etRegConfirmPassword.text.toString()

            // Check passwords match
            if (password != confirmPassword) {
                binding.etRegConfirmPassword.error = "Les mots de passe ne correspondent pas"
                binding.etRegConfirmPassword.requestFocus()
                return@setOnClickListener
            }

            // TODO: Handle registration logic
        }
    }
}
