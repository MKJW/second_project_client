package com.mksoft.mkjw_second_project.viewmodel

import androidx.lifecycle.MutableLiveData
import com.mksoft.mkjw_second_project.R
import com.mksoft.mkjw_second_project.base.BaseViewModel
import com.mksoft.mkjw_second_project.model.Board.BoardContents
import com.mksoft.mkjw_second_project.model.SNS.Feed

class FeedItemViewModel :BaseViewModel(){
    var currentClickState:Boolean = false//여기에 유저가 클릭한 피드인지 상태를 저장
    var currentStarNum:Int = 0
    val likeCntNum: MutableLiveData<String> = MutableLiveData()
    val imageSrc: MutableLiveData<String> = MutableLiveData()


    fun bind(feedItem: Feed) {
        currentStarNum = feedItem.likeCntNum!!.toInt()
        likeCntNum.value = feedItem.likeCntNum.toString()
        imageSrc.value = feedItem.imageSrc
    }
    fun starClick():Int{
        return if(!currentClickState){
            currentClickState = true
            currentStarNum++
            likeCntNum.value = currentStarNum.toString()
            R.drawable.baseline_star_black_48dp
        }else{
            currentClickState = false
            currentStarNum--
            likeCntNum.value = currentStarNum.toString()
            R.drawable.baseline_star_border_black_48dp
        }
    }

}