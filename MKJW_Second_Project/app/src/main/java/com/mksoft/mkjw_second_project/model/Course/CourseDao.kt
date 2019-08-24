package com.mksoft.mkjw_second_project.model.Course

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface CourseDao {
    //코스 관련 질의

    @get:Query("SELECT * FROM course")
    val getAllCourses: List<Course>

    @Insert(onConflict = REPLACE)
    fun insertCourse(vararg course:Course)
}