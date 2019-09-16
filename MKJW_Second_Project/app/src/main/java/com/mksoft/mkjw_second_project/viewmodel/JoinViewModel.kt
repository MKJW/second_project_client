package com.mksoft.mkjw_second_project.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.mksoft.mkjw_second_project.R
import com.mksoft.mkjw_second_project.api.JoinAPI
import com.mksoft.mkjw_second_project.api.LoginAPI
import com.mksoft.mkjw_second_project.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.join_page_activity.*
import javax.inject.Inject

class JoinViewModel :BaseViewModel(){

    @Inject
    lateinit var joinAPI: JoinAPI

    private val inputIDState : MutableLiveData<Int> = MutableLiveData()
    private val inputPWState : MutableLiveData<Int> = MutableLiveData()

    private val inputIDStateColor : MutableLiveData<Int> = MutableLiveData()
    private val inputPWStateColor : MutableLiveData<Int> = MutableLiveData()

    private lateinit var subscription: Disposable

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }



    fun checkIDState(IDState : Boolean, inputID:String){
        if(IDState){
            checkOverlapID(inputID)
        }else{
            notProperIDState()
        }
    }
    @SuppressLint("CheckResult")
    private fun checkOverlapID(inputID:String){
        //우선 서버로 요청하여 중복을 검사하고 중복 되는 아이디가 존재하면overlapIDState을 호출
        //그밖의 경우는 properIDState호출하자
        joinAPI.checkOverlapID(inputID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result->
                if(!result) {
                    //겹치는게 true
                    properIDState()
                }else{
                    overlapIDState()
                }

            }

    }
    fun checkPWState(PWState : Boolean){
        if(PWState){
            properPWState()
        }else{
            notProperPWState()
        }
    }


    private fun properIDState(){
        inputIDState.value = R.string.proper_id_state
        inputIDStateColor.value = R.color.sucessColor
    }
    private fun properPWState(){
        inputPWState.value = R.string.proper_pw_state
        inputPWStateColor.value = R.color.sucessColor
    }
    private fun notProperIDState(){
        inputIDState.value = R.string.not_proper_id_state
        inputIDStateColor.value = R.color.failColor
    }
    private fun notProperPWState(){
        inputPWState.value = R.string.not_proper_pw_state
        inputPWStateColor.value = R.color.failColor
    }
    private fun overlapIDState(){
        inputIDState.value = R.string.overlap_id_state
        inputIDStateColor.value=R.color.failColor
    }
}