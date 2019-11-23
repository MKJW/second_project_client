package com.mksoft.mkjw_second_project.api

import com.mksoft.mkjw_second_project.model.Board.BoardCategoryContents
import com.mksoft.mkjw_second_project.model.Board.BoardContents

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface BoardAPI {
    @GET("/notices/{studentId}")
    fun getAllNotice(
        @Path("studentId")studentId:String
    ): Observable<List<BoardContents>>
}