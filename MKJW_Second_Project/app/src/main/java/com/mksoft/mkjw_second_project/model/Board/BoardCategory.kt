package com.mksoft.mkjw_second_project.model.Board

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*


@Entity
data class BoardCategory(
    @field:PrimaryKey val categoryName:String,
    var notYetReadContentsCount:Int?=0
)