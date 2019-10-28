package com.mksoft.mkjw_second_project.model.Board

import androidx.room.Ignore

data class BoardCategoryContents(
    val boardCategory: BoardCategory,
    val boardContentsList:MutableList<BoardContents>
)
//이렇게 분리시킨 이유는 리스트를 room에 넣을 때 @Ignore을 하려고 했지만...