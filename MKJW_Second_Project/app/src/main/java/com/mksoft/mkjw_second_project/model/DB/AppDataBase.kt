package com.mksoft.mkjw_second_project.model.DB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mksoft.mkjw_second_project.model.Board.BoardCategory
import com.mksoft.mkjw_second_project.model.Board.BoardCategoryDao
import com.mksoft.mkjw_second_project.model.Course.Course
import com.mksoft.mkjw_second_project.model.Course.CourseDao
import com.mksoft.mkjw_second_project.model.Course.StudentCourse
import com.mksoft.mkjw_second_project.model.Course.StudentCourseDao
import com.mksoft.mkjw_second_project.model.Section.Section
import com.mksoft.mkjw_second_project.model.Section.SectionDao
import com.mksoft.mkjw_second_project.model.Section.TimeLocation
import com.mksoft.mkjw_second_project.model.Section.TimeLocationDao

@Database(entities = [Course::class, StudentCourse::class, Section::class, TimeLocation::class, BoardCategory::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun courseDao(): CourseDao
    abstract fun studentCourseDao(): StudentCourseDao
    abstract fun sectionDao(): SectionDao
    abstract fun timeLocationDao(): TimeLocationDao
    abstract fun boardCategoryDao(): BoardCategoryDao
}