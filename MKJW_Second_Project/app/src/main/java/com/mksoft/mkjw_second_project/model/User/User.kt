package com.mksoft.mkjw_second_project.model.User

import androidx.room.Entity

@Entity(primaryKeys = ["user_id"])
data class User(
    val user_id:String,
    val school_id:String,
    val type : String,
    val firstName:String,
    val lastName:String,
    val email:String,
    val phone:String
)