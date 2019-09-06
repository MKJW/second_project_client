package com.mksoft.mkjw_second_project.api

import com.mksoft.mkjw_second_project.model.Course.Course
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JoinAPI{
    @GET("")
    fun checkOverlapID(
        @Query("user_id") user_id: String
    ): Observable<Boolean>
}