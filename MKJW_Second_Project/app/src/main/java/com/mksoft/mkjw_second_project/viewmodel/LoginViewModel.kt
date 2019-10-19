package com.mksoft.mkjw_second_project.viewmodel

import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.mksoft.mkjw_second_project.api.LoginAPI
import com.mksoft.mkjw_second_project.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel:BaseViewModel(){
    @Inject
    lateinit var loginAPI: LoginAPI

    fun login(userName:String, password:String){
        loginAPI.login(userName, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                    result-> Log.d("Login",result.toString())}
                ,{})

    }


}