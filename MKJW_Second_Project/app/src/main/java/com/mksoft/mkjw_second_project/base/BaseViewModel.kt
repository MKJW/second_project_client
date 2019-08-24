package com.mksoft.mkjw_second_project.base

import android.app.Application
import androidx.lifecycle.ViewModel
import com.mksoft.mkjw_second_project.App
import com.mksoft.mkjw_second_project.di.component.AppComponent
import com.mksoft.mkjw_second_project.di.component.DaggerAppComponent
import com.mksoft.mkjw_second_project.di.module.DataBaseModule
import com.mksoft.mkjw_second_project.di.module.NetworkModule
import com.mksoft.mkjw_second_project.viewmodel.RegisterCourseListViewModel
import com.mksoft.mkjw_second_project.viewmodel.RegisterCourseViewModel

abstract class BaseViewModel: ViewModel(){
    private val injector:AppComponent =
        DaggerAppComponent.builder()
            .dataBaseModule(DataBaseModule(App.applicationContext() as Application))
            .networkModule(NetworkModule())
            .build()//여기서 한번 초기화시켜주자

    init{
        inject()
    }
    private fun inject(){
        when(this){
            is RegisterCourseListViewModel -> injector.inject(this)
            is RegisterCourseViewModel -> injector.inject(this)
        }
    }
}
//주입을 위한 베이스 뷰모델