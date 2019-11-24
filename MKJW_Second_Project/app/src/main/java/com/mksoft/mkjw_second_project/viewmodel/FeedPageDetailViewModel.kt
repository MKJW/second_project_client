package com.mksoft.mkjw_second_project.viewmodel

import androidx.lifecycle.MutableLiveData
import com.mksoft.mkjw_second_project.R
import com.mksoft.mkjw_second_project.base.BaseViewModel
import com.mksoft.mkjw_second_project.model.SNS.Feed

class FeedPageDetailViewModel :BaseViewModel(){
    var currentClickState:Boolean = false//여기에 유저가 클릭한 피드인지 상태를 저장
    var currentStarNum:Int = 0
    val likeCntNum: MutableLiveData<String> = MutableLiveData()
    val imageSrc: MutableLiveData<String> = MutableLiveData()


    fun bindFeedItem(currentClickState:Boolean, currentStarNum:Int, imageSrc:String){
        this.currentClickState = currentClickState
        this.currentStarNum = currentStarNum
        likeCntNum.value = currentStarNum.toString()
        this.imageSrc.value = imageSrc

    }



}