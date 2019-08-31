package com.mksoft.mkjw_second_project.api

import com.mksoft.mkjw_second_project.model.Course.Course
import com.mksoft.mkjw_second_project.model.Course.Student_Course
import io.reactivex.Observable
import retrofit2.http.*

interface RegisterCourseAPI{
    //등록과 코스 관련 api
    @GET("/courses/{school_id}/{grade}")
    fun getCourses(
        @Path("school_id") school_id:String,
        @Path("grade") grade:String
    ): Observable<List<Course>>

    @POST("/courses/register")
    fun postRegisterCourse(
        @Header("course_id") course_id: String,
        @Header("student_id") student_id: String
    ): Observable<String>

    @POST("/courses/check")
    fun checkCourse(
        @Header("course_id") course_id: String,
        @Header("student_id") student_id: String

        ): Observable<Boolean>

    @POST("/courses/unregister")
    fun postUnregisterCourse(
        @Header("course_id") course_id: String,
        @Header("student_id") student_id: String
    ): Observable<String>

}