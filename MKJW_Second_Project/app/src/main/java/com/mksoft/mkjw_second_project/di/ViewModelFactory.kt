package com.mksoft.mkjw_second_project.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mksoft.mkjw_second_project.viewmodel.RegisterCourseListViewModel

class ViewModelFactory: ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterCourseListViewModel::class.java)){
            return RegisterCourseListViewModel() as T
        }


        throw IllegalAccessException("Unknown ViewModel...")
    }

}