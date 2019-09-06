package com.mksoft.mkjw_second_project.model.Course

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface Studnt_CourseDao {
    //코스 관련 질의

    @Query("SELECT * FROM Student_Course")
    fun getAllStudent_Course(): List<Student_Course>

    @Query("SELECT COUNT(*) FROM Student_Course WHERE course_id=:course_id")
    fun getStudent_Course (course_id:String):Int

    @Insert(onConflict = REPLACE)
    fun insertStudent_Course(vararg studentCourse: Student_Course)

    @Delete
    fun delete(student_course:Student_Course)
}