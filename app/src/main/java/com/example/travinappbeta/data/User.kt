package com.example.travinappbeta.data

import androidx.room.Entity
import  androidx.room.PrimaryKey

@Entity(tableName =  "user_table")
class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName: String,
    val cellphone : String,
    val index: Double
)