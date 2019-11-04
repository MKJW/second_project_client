package com.mksoft.mkjw_second_project.model.Board

import java.util.*

data class BoardContents(
    val id:Int?=-1,
    val title:String?="null",
    val content:String?="null",
    val createdDate:Long
)