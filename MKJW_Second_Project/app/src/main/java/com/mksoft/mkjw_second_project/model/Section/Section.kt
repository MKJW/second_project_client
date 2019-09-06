package com.mksoft.mkjw_second_project.model.Section

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["course_id", "section_id"])
data class Section(
    val section_id:String,
    val course_id: String,
    val teacher_id: String,
    val number_of_student: Int
)