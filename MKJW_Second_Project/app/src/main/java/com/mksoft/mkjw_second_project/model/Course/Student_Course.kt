package com.mksoft.mkjw_second_project.model.Course

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = [/*"school_id",*/ "course_id", "student_id"])
data class Student_Course(
    //val school_id: String,
    val course_id: String,
    val student_id: String
)//학생이 과목을 등록하고 성공한다면 디비에 저장을 하자
//이미 코스를 받을 때 그레이드가 강제시 되었기 때문에 코스와 학생 관계에서 그레이드가 필요할까?

