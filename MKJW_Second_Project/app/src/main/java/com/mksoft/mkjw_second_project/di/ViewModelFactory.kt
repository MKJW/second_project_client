package com.mksoft.mkjw_second_project.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mksoft.mkjw_second_project.viewmodel.JoinViewModel
import com.mksoft.mkjw_second_project.viewmodel.LoginViewModel
import com.mksoft.mkjw_second_project.viewmodel.RegisterCourseListViewModel
import com.mksoft.mkjw_second_project.viewmodel.TimeTableViewModel
import java.sql.Time

class ViewModelFactory: ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterCourseListViewModel::class.java)){
            return RegisterCourseListViewModel() as T
        }else if(modelClass.isAssignableFrom(TimeTableViewModel::class.java)){
            return TimeTableViewModel() as T
        }else if(modelClass.isAssignableFrom(JoinViewModel::class.java)){
            return JoinViewModel() as T
        }else if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel() as T
        }


        throw IllegalAccessException("Unknown ViewModel...")
    }

}