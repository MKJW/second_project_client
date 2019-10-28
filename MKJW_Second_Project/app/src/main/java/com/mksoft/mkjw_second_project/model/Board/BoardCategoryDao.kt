package com.mksoft.mkjw_second_project.model.Board

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mksoft.mkjw_second_project.model.Course.Course

@Dao
interface BoardCategoryDao {
    @Query("SELECT * FROM BoardCategory WHERE categoryName")
    fun getCategory(): List<BoardCategory>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBoardCategory(vararg boardCategory: BoardCategory)

}//세션의 내용과 겹치는 부분이 많아보이지만... 게시판에는 학급이나 혹은 그 이후 확장성을 고려하여 따로 뺌...