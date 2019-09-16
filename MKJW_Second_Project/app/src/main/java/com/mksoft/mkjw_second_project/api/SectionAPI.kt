package com.mksoft.mkjw_second_project.api

import com.mksoft.mkjw_second_project.model.Course.Course
import com.mksoft.mkjw_second_project.model.Section.Section
import com.mksoft.mkjw_second_project.model.Section.TimeLocation
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface SectionAPI{
    //여기서 세션관련과 시간 관련도 여기서 처리하자
    @GET("")
    fun getSections(

    ): Observable<List<Section>>

    @GET()
    fun getTimeLocation(

    ):Observable<List<TimeLocation>>

}