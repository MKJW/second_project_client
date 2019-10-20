package com.mksoft.mkjw_second_project.model.Board

import androidx.room.Entity


@Entity(primaryKeys = ["rootCategory", "currentCategory"])
data class BoardCategory(
    val rootCategory:String,
    val currentCategory:String,
    var notYetReadContentsCount:Int
)