package com.mksoft.mkjw_second_project.viewmodel

import androidx.lifecycle.MutableLiveData
import com.mksoft.mkjw_second_project.base.BaseViewModel
import com.mksoft.mkjw_second_project.model.Board.BoardContents
import com.mksoft.mkjw_second_project.model.SNS.Feed

class FeedItemViewModel :BaseViewModel(){
    val likeCntNum: MutableLiveData<String> = MutableLiveData()
    val imageSrc: MutableLiveData<String> = MutableLiveData()


    fun bind(feedItem: Feed) {
        likeCntNum.value = feedItem.likeCntNum.toString()
        imageSrc.value = feedItem.imageSrc

    }

}