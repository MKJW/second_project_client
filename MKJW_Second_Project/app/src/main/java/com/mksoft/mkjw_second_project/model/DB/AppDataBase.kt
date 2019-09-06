package com.mksoft.mkjw_second_project.model.DB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mksoft.mkjw_second_project.model.Course.Course
import com.mksoft.mkjw_second_project.model.Course.CourseDao
import com.mksoft.mkjw_second_project.model.Course.Student_Course
import com.mksoft.mkjw_second_project.model.Course.Studnt_CourseDao
import com.mksoft.mkjw_second_project.model.Section.Section
import com.mksoft.mkjw_second_project.model.Section.SectionDao
import com.mksoft.mkjw_second_project.model.Section.Time_Location
import com.mksoft.mkjw_second_project.model.Section.Time_LocationDao

@Database(entities = [Course::class, Student_Course::class, Section::class, Time_Location::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun courseDao(): CourseDao
    abstract fun student_courseDao(): Studnt_CourseDao
    abstract fun sectionDao(): SectionDao
    abstract fun time_locationDao(): Time_LocationDao
}