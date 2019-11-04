package com.mksoft.mkjw_second_project.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.mksoft.mkjw_second_project.base.BaseViewModel
import com.mksoft.mkjw_second_project.model.Board.BoardCategory
import com.mksoft.mkjw_second_project.model.Board.BoardContents
import com.mksoft.mkjw_second_project.model.DB.AppDataBase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BoardContentsItemViewModel :BaseViewModel(){

    val writer: MutableLiveData<String> = MutableLiveData()
    val contents: MutableLiveData<String> = MutableLiveData()
    val imageSrc:MutableLiveData<String> = MutableLiveData()
    private lateinit var boardContents: BoardContents


    fun bind(boardContents: BoardContents) {
        this.boardContents = boardContents
        writer.value = this.boardContents.id.toString()
        contents.value = this.boardContents.content

    }


}