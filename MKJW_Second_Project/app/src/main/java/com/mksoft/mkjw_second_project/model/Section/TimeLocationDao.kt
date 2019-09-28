package com.mksoft.mkjw_second_project.model.Section

import androidx.room.*

@Dao
interface TimeLocationDao {
    @Query("SELECT * FROM TimeLocation WHERE section_id LIKE :sectionID")
    fun getTimeLocation(sectionID:String): List<TimeLocation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTimeLocation(vararg timeLocation: TimeLocation)
}