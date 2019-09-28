package com.mksoft.mkjw_second_project.model.User

import androidx.room.Entity

@Entity(primaryKeys = ["user_id"])
data class User(
    val firstName:String,
    val lastName:String,
    val email:String,
    val password:String,
    val matchingPassword:String

)