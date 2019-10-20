package com.mksoft.mkjw_second_project.model.Board

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mksoft.mkjw_second_project.model.Course.Course

@Dao
interface BoardCategoryDao {
    @Query("SELECT * FROM BoardCategory WHERE rootCategory LIKE :rootCategory")
    fun getCategory(rootCategory: String): List<BoardCategory>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBoardCategory(vararg boardCategory: BoardCategory)

}