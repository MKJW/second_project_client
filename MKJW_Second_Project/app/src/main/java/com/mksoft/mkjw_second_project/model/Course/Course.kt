package com.mksoft.mkjw_second_project.model.Course

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Course(
    @field:PrimaryKey
    val course_id: String,
    val course_name: String
)