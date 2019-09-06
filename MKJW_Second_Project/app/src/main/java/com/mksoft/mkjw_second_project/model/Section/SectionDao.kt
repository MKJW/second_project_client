package com.mksoft.mkjw_second_project.model.Section

import androidx.room.*
import com.mksoft.mkjw_second_project.model.Course.Course

@Dao
interface SectionDao {

    @Query("SELECT * FROM section")
    fun getSections(): List<Section>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSection(vararg sections: Section)
}