package com.isetr.cupcake.data.repository

import android.content.Context
import com.isetr.cupcake.data.local.AppDatabase
import com.isetr.cupcake.data.local.UserEntity

class AuthRepository(context: Context) {

    private val userDao = AppDatabase.getInstance(context).userDao()

    // Register a user
    suspend fun registerUser(user: UserEntity): Boolean {
        val existing = userDao.getUserByEmail(user.email)
        return if (existing == null) {
            userDao.insertUser(user)
            true
        } else false
    }

    // Login a user
    suspend fun loginUser(email: String, password: String): UserEntity? {
        return userDao.login(email, password)
    }
}
