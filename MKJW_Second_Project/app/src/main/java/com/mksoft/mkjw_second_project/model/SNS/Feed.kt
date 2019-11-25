package com.mksoft.mkjw_second_project.model.SNS

data class Feed(
    val feedID:String ?= "",
    val imageSrc:String?="",
    var likeCntNum:Int?=0,
    val contents:String?="null",
    val writer:String ?="null"
)