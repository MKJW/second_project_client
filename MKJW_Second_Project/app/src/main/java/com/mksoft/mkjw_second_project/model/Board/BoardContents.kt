package com.mksoft.mkjw_second_project.model.Board

import java.util.*

data class BoardContents(
    val id:String?="test",
    val title:String?="test",
    val content:String?="test",
    val category:String?="test",
    val createdDate:Long,
    val courseId:String ?="test"
)