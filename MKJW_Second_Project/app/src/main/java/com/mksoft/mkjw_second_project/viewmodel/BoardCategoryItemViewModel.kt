package com.mksoft.mkjw_second_project.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.mksoft.mkjw_second_project.base.BaseViewModel
import com.mksoft.mkjw_second_project.model.Board.BoardCategory
import com.mksoft.mkjw_second_project.model.DB.AppDataBase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BoardCategoryItemViewModel :BaseViewModel(){


    @Inject
    lateinit var appDataBase: AppDataBase


    val categoryName:MutableLiveData<String> = MutableLiveData()
    val notYetReadCount:MutableLiveData<String> = MutableLiveData()
    val newStateVisibility:MutableLiveData<Int> = MutableLiveData()
    lateinit var boardCategory:BoardCategory
    private lateinit var subscription: Disposable

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    fun bind(boardCategory:BoardCategory) {
        this.boardCategory = boardCategory
        categoryName.value = this.boardCategory.currentCategory
        if(this.boardCategory.notYetReadContentsCount == 0){
            newStateVisibility.value = View.GONE
        }else{
            newStateVisibility.value = View.VISIBLE
            notYetReadCount.value = this.boardCategory.notYetReadContentsCount.toString()
        }
    }
    fun clickView(){
        newStateVisibility.value = View.GONE
        readContents()
    }
    private fun readContents(){
        boardCategory.notYetReadContentsCount = 0
        insertBoardCategory(boardCategory)
    }
    //여기서 눌러지면 내부에 저장되어있는 아직 읽지 않은 상태를 초기화 시켜주자
    private fun insertBoardCategory(boardCategory: BoardCategory) {
        subscription = Observable.fromCallable {
            appDataBase.boardCategoryDao().insertBoardCategory(
                boardCategory
            )
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
            }
    }
}