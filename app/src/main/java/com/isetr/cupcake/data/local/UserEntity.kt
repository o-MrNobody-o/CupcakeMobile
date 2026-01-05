package com.isetr.cupcake.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nom: String,
    val prenom: String,
    val email: String,
    val adresse: String,
    val telephone: String,
    val password: String
)
