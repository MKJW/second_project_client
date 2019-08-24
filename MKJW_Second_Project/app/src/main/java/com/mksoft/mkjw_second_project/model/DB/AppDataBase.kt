package com.mksoft.mkjw_second_project.model.DB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mksoft.mkjw_second_project.model.Course.Course
import com.mksoft.mkjw_second_project.model.Course.CourseDao
import com.mksoft.mkjw_second_project.model.Course.Student_Course
import com.mksoft.mkjw_second_project.model.Course.Studnt_CourseDao

@Database(entities = [Course::class, Student_Course::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun courseDao(): CourseDao
    abstract fun student_courseDao(): Studnt_CourseDao
}