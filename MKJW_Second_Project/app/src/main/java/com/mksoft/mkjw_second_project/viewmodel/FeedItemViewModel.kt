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
    private var feedId:String = ""
    val likeCntNum: MutableLiveData<String> = MutableLiveData()
    val imageSrc: MutableLiveData<String> = MutableLiveData()
    val starImageState:MutableLiveData<Int> = MutableLiveData()

    fun updateViewModel(currentClickState: Boolean, currentStarNum: Int){
        this.currentClickState = currentClickState
        this.currentStarNum = currentStarNum
        bindingStarNumAndState(currentClickState, currentStarNum)
    }

    fun bind(feedItem: Feed) {
        //내부에서 스타 눌렀는지 상태를 확인하고 여기서 바인딩하자
        this.feedId = feedItem.feedID?:""
        currentStarNum = feedItem.likeCntNum!!.toInt()
        imageSrcString = feedItem.imageSrc!!
        imageSrc.value = feedItem.imageSrc
        bindingStarNumAndState(currentClickState, feedItem.likeCntNum?:0)
    }
    private fun bindingStarNumAndState(currentClickState:Boolean, currentStarNum: Int){
        if(currentClickState){
            starImageState.value = R.drawable.baseline_star_black_48dp
        }else{
            starImageState.value = R.drawable.baseline_star_border_black_48dp
        }
        likeCntNum.value = currentStarNum.toString()
    }
    fun getImageSrcString():String{
        return imageSrcString
    }
    fun getFeedId():String{
        return this.feedId
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