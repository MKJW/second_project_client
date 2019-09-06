package com.mksoft.mkjw_second_project.viewmodel

import com.mksoft.mkjw_second_project.api.LoginAPI
import com.mksoft.mkjw_second_project.base.BaseViewModel
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class LoginViewModel:BaseViewModel(){
    @Inject
    lateinit var loginAPI: LoginAPI
    private lateinit var subscription: Disposable


    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }


}