package com.example.travinappbeta.data

import androidx.room.Entity
import  androidx.room.PrimaryKey

@Entity(tableName =  "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val firstName: String,
    val cellphone : String,
    val beforew: Double,
    val afterw: Double,
    val edited: Boolean = false

)