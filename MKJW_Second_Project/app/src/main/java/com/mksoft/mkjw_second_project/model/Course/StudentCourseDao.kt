package com.mksoft.mkjw_second_project.model.Course

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface StudentCourseDao {
    //코스 관련 질의

    @Query("SELECT * FROM StudentCourse")
    fun getAllStudentCourse(): List<StudentCourse>

    @Query("SELECT COUNT(*) FROM StudentCourse WHERE course_id=:course_id")
    fun getStudentCourse (course_id:String):Int

    @Insert(onConflict = REPLACE)
    fun insertStudentCourse(vararg studentCourse: StudentCourse)

    @Delete
    fun delete(student_course:StudentCourse)
}