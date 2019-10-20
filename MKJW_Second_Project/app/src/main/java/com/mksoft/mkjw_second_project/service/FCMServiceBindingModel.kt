package com.mksoft.mkjw_second_project.service

import android.util.Log
import com.mksoft.mkjw_second_project.api.FCMAPI
import com.mksoft.mkjw_second_project.base.BaseViewModel
import com.mksoft.mkjw_second_project.ui_view.CourseListAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FCMServiceBindingModel : BaseViewModel(){

    @Inject
    lateinit var fcmapi: FCMAPI

    fun sendToken(token:String){
        fcmapi.sendFCMToken(token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                    result-> Log.d("token is successful?:",result.toString())}
                ,{})

    }
    //메시지 분류에 따라서 내부 디비에 카운트를 갱신하자





}