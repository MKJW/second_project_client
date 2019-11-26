package com.mksoft.mkjw_second_project.viewmodel

import androidx.lifecycle.MutableLiveData
import com.mksoft.mkjw_second_project.R
import com.mksoft.mkjw_second_project.base.BaseViewModel
import com.mksoft.mkjw_second_project.model.SNS.Feed

class FeedPageDetailViewModel :BaseViewModel(){
    private var currentClickState:Boolean = false//여기에 유저가 클릭한 피드인지 상태를 저장
    private var currentStarNum:Int = 0
    private lateinit var feedId:String
    val likeCntNum: MutableLiveData<String> = MutableLiveData()
    val imageSrc: MutableLiveData<String> = MutableLiveData()
    val starImageState:MutableLiveData<Int> = MutableLiveData()


    fun getCurrentClickState():Boolean{
        return currentClickState
    }
    fun getCurrentStarNum():Int{
        return currentStarNum
    }
    fun getFeedId():String{
        return feedId
    }
    fun bindFeedItem(currentClickState:Boolean, currentStarNum:Int, imageSrc:String, feedId:String){
        this.feedId = feedId
        this.currentClickState = currentClickState
        this.currentStarNum = currentStarNum
        likeCntNum.value = currentStarNum.toString()
        this.imageSrc.value = imageSrc
        if(currentClickState){
            starImageState.value = R.drawable.baseline_star_black_48dp
        }else{
            starImageState.value = R.drawable.baseline_star_border_black_48dp
        }

    }
    fun starClick(){
        if(!currentClickState){
            currentClickState = true
            currentStarNum++
            likeCntNum.value = currentStarNum.toString()
            starImageState.value = R.drawable.baseline_star_black_48dp
        }else{
            currentClickState = false
            currentStarNum--
            likeCntNum.value = currentStarNum.toString()
            starImageState.value = R.drawable.baseline_star_border_black_48dp
        }
    }


}