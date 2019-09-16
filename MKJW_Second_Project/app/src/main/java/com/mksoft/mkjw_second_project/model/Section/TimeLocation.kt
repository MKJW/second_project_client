package com.mksoft.mkjw_second_project.model.Section

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["day", "start_time", "end_time"])
data class TimeLocation(
    val day:String,
    val start_time:String,
    val end_time:String,
    val location:String,
    val section_id: String
)