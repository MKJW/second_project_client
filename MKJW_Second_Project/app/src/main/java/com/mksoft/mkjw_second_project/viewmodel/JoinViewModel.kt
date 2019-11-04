package com.mksoft.mkjw_second_project.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mksoft.mkjw_second_project.App
import com.mksoft.mkjw_second_project.R
import com.mksoft.mkjw_second_project.api.JoinAPI
import com.mksoft.mkjw_second_project.api.LoginAPI
import com.mksoft.mkjw_second_project.base.BaseViewModel
import com.mksoft.mkjw_second_project.model.User.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.join_page_activity.*
import javax.inject.Inject

class JoinViewModel :BaseViewModel(){

    @Inject
    lateinit var joinAPI: JoinAPI

    val inputIDState : MutableLiveData<String> = MutableLiveData()
    val inputPWState : MutableLiveData<String> = MutableLiveData()

    val inputIDStateColor : MutableLiveData<Int> = MutableLiveData()
    val inputPWStateColor : MutableLiveData<Int> = MutableLiveData()

    var idState : Boolean = false
    var pwState : Boolean = false




    @SuppressLint("CheckResult")
    fun sendUserForJoin(email:String, pw:String, name:String){
        if(idState && pwState && name.length>=2){

            joinAPI.join(name.substring(1, name.length-1), name.substring(0,1), pw, pw, email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                        result-> Log.d("Login",result.toString())}
                    ,{err -> Log.d("sendUserForJoin", err.toString())})


        }

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
            .subscribe ({ result->
                if(!result) {
                    //겹치는게 true
                    properIDState()
                }else{
                    overlapIDState()
                }

            },
                {err -> Log.d("checkOverlapID", err.toString())})

    }
    fun checkPWState(PWState : Boolean){
        if(PWState){
            properPWState()
        }else{
            notProperPWState()
        }
    }
    fun checkSamePW(PWState: Boolean){
        if(PWState){
            properPWState()
        }else{
            notProperCheckPWState()
        }
    }


    private fun properIDState(){
        inputIDState.value = App.applicationContext().resources.getString(R.string.proper_id_state)
        inputIDStateColor.value = R.color.sucessColor
        idState = true
    }
    private fun properPWState(){
        inputPWState.value = App.applicationContext().resources.getString(R.string.proper_pw_state)
        inputPWStateColor.value = R.color.sucessColor
        pwState = true
    }
    private fun notProperIDState(){
        inputIDState.value = App.applicationContext().resources.getString(R.string.not_proper_id_state)
        inputIDStateColor.value = R.color.failColor
        idState = false
    }
    private fun notProperPWState(){
        inputPWState.value = App.applicationContext().resources.getString(R.string.not_proper_pw_state)
        inputPWStateColor.value = R.color.failColor
        pwState = false
    }
    private fun notProperCheckPWState(){
        inputPWState.value = App.applicationContext().resources.getString(R.string.not_same_pw_state)
        inputPWStateColor.value = R.color.failColor
        pwState = false
    }
    private fun overlapIDState(){
        inputIDState.value = App.applicationContext().resources.getString(R.string.overlap_id_state)
        inputIDStateColor.value=R.color.failColor
        idState = true
    }
}