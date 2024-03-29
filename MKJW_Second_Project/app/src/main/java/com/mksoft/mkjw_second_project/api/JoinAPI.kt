package com.mksoft.mkjw_second_project.api

import com.mksoft.mkjw_second_project.model.Course.Course
import com.mksoft.mkjw_second_project.model.User.User
import io.reactivex.Observable
import retrofit2.http.*

interface JoinAPI{
    @GET("/user/email/exists")
    fun checkOverlapID(
        @Query("email") user_id: String
    ): Observable<Boolean>
    @FormUrlEncoded
    @POST("/user/registration")
    fun join(
        @Field("firstName") firstName:String,
        @Field("lastName") lastName:String,
        @Field("password") password:String,
        @Field("matchingPassword") matchingPassword:String,
        @Field("email") email:String
    ):Observable<Boolean>
}