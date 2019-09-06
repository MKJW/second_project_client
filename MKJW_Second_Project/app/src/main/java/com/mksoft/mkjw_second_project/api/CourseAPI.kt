package com.mksoft.mkjw_second_project.api

import com.mksoft.mkjw_second_project.model.Course.Course
import com.mksoft.mkjw_second_project.model.Course.Student_Course
import io.reactivex.Observable
import retrofit2.http.*

interface CourseAPI{
    //코스 관련 api
    @GET("/courses/{school_id}/{grade}")
    fun getCourses(
        @Path("school_id") school_id:String,
        @Path("grade") grade:String
    ): Observable<List<Course>>

    @POST("/courses/register")
    fun postRegisterCourse(
        @Query("course_id") course_id: String,
        @Query("student_id") student_id: String
    ): Observable<String>

    @GET("/courses/check")
    fun checkCourse(
        @Query("course_id") course_id: String,
        @Query("student_id") student_id: String

        ): Observable<Boolean>

    @POST("/courses/unregister")
    fun postUnregisterCourse(
        @Query("course_id") course_id: String,
        @Query("student_id") student_id: String
    ): Observable<Boolean>

}