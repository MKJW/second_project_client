package com.mksoft.mkjw_second_project.api

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface FCMAPI {

    @FormUrlEncoded
    @POST("/fcm/test/notification")
    fun sendFCMToken(
        @Field("device-token") deviceToken: String
    ): Observable<Boolean>

}