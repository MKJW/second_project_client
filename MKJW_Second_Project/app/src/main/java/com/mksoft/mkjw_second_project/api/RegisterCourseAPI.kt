package com.mksoft.mkjw_second_project.api

import com.mksoft.mkjw_second_project.model.Course.Course
import com.mksoft.mkjw_second_project.model.Course.Student_Course
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RegisterCourseAPI{
    //등록과 코스 관련 api
    @GET("/courses/{school_id}/{grade}")
    fun getCourses(
        @Path("school_id") school_id:String,
        @Path("grade") grade:String
    ): Observable<List<Course>>

    @POST("/courses/register")
    fun postRegisterCourse(
        @Body studentCourse: Student_Course
    ): Observable<String>

    @POST("/courses/check")
    fun checkCourse(
        @Body studentCourse: Student_Course
    ): Observable<Boolean>

    @POST("/courses/unregister")
    fun postUnregisterCourse(
        @Body studentCourse: Student_Course
    ): Observable<String>

}