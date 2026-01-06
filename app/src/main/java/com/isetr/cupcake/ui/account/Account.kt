package com.isetr.cupcake.ui.account

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.isetr.cupcake.R
import com.isetr.cupcake.data.local.UserEntity
import com.isetr.cupcake.databinding.ActivityAccountBinding
import com.isetr.cupcake.ui.auth.AuthActivity
import com.isetr.cupcake.viewmodel.AccountViewModel

class AccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountBinding
    private lateinit var viewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_account)

        viewModel = ViewModelProvider(this, AccountViewModel.Factory(application))
            .get(AccountViewModel::class.java)

        // Observe current user and bind
        viewModel.currentUser.observe(this) { user ->
            user?.let {
                binding.user = it // Bind the whole UserEntity object
            }
        }


        // Observe messages (Toast)
        viewModel.message.observe(this) { msg ->
            msg?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                viewModel.clearMessage()
            }
        }

        // Load current user
        viewModel.loadCurrentUser()

        // Buttons
        binding.btnUpdateAccount.setOnClickListener {
            binding.user?.let { user ->
                viewModel.updateUserInfo(
                    nom = user.nom,
                    prenom = user.prenom,
                    email = user.email,
                    adresse = user.adresse,
                    telephone = user.telephone
                )
            }
        }

        binding.btnDeleteAccount.setOnClickListener {
            viewModel.deleteAccount {
                val intent = Intent(this, AuthActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
        }

        binding.btnLogout.setOnClickListener {
            viewModel.logout()
            val intent = Intent(this, AuthActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}
