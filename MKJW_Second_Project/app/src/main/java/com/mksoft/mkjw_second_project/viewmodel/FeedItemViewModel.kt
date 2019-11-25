package com.mksoft.mkjw_second_project.viewmodel

import androidx.lifecycle.MutableLiveData
import com.mksoft.mkjw_second_project.R
import com.mksoft.mkjw_second_project.base.BaseViewModel
import com.mksoft.mkjw_second_project.model.Board.BoardContents
import com.mksoft.mkjw_second_project.model.SNS.Feed

class FeedItemViewModel :BaseViewModel(){
    var currentClickState:Boolean = false//여기에 유저가 클릭한 피드인지 상태를 저장
    var currentStarNum:Int = 0
    private var imageSrcString:String = ""
    val likeCntNum: MutableLiveData<String> = MutableLiveData()
    val imageSrc: MutableLiveData<String> = MutableLiveData()
    val starImageState:MutableLiveData<Int> = MutableLiveData()


    fun bind(feedItem: Feed) {
        //내부에서 스타 눌렀는지 상태를 확인하고 여기서 바인딩하자
        currentStarNum = feedItem.likeCntNum!!.toInt()
        imageSrcString = feedItem.imageSrc!!
        likeCntNum.value = feedItem.likeCntNum.toString()
        imageSrc.value = feedItem.imageSrc
        if(currentClickState){
            starImageState.value = R.drawable.baseline_star_black_48dp
        }else{
            starImageState.value = R.drawable.baseline_star_border_black_48dp
        }
    }
    fun getImageSrcString():String{
        return imageSrcString
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