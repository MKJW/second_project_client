package com.mksoft.mkjw_second_project.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.mksoft.mkjw_second_project.base.BaseViewModel
import com.mksoft.mkjw_second_project.model.Board.BoardCategory
import com.mksoft.mkjw_second_project.model.Board.BoardCategoryContents
import com.mksoft.mkjw_second_project.model.Board.BoardContents
import com.mksoft.mkjw_second_project.model.DB.AppDataBase
import com.mksoft.mkjw_second_project.ui_view.BoardContentsListAdapter
import com.mksoft.mkjw_second_project.utils.collapse
import com.mksoft.mkjw_second_project.utils.expand
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
    val upButtonVisibility:MutableLiveData<Int> = MutableLiveData()
    val downButtonVisibility:MutableLiveData<Int> = MutableLiveData()
    var expandState = false

    lateinit var boardCategoryContents:BoardCategoryContents
    private lateinit var subscription: Disposable

    val boardContentsListAdapter:BoardContentsListAdapter = BoardContentsListAdapter()
    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    init {

    }
    fun bind(boardCategoryContents:BoardCategoryContents) {
        this.boardCategoryContents = boardCategoryContents
        categoryName.value = this.boardCategoryContents.boardCategory.categoryName
        if(this.boardCategoryContents.boardCategory.notYetReadContentsCount == 0){
            newStateVisibility.value = View.GONE
        }else{
            newStateVisibility.value = View.VISIBLE
            notYetReadCount.value = this.boardCategoryContents.boardCategory.notYetReadContentsCount.toString()//읽지 않은 수
        }
        yetExpandButtonState()
        //리사이클러뷰 바인딩
        updateBoardContents(boardCategoryContents.boardContentsList!!)

    }
    private fun expandButtonState(){
        upButtonVisibility.value = View.VISIBLE
        downButtonVisibility.value = View.GONE

    }
    private fun yetExpandButtonState(){
        upButtonVisibility.value = View.GONE
        downButtonVisibility.value = View.VISIBLE

    }
    fun clickView(expandView:View){

        newStateVisibility.value = View.GONE
        readContents()//new 마크 갱신

        if(!expandState){
            expand(expandView)
            expandState = true
            expandButtonState()
        }else{

            collapse(expandView)
            expandState = false
            yetExpandButtonState()
        }
    }
    private fun readContents(){
        boardCategoryContents.boardCategory.notYetReadContentsCount = 0
        insertBoardCategory(boardCategoryContents.boardCategory)
    }
    //여기서 눌러지면 내부에 저장되어있는 아직 읽지 않은 상태를 초기화 시켜주자
    private fun insertBoardCategory(boardCategory: BoardCategory) {
        subscription = Observable.fromCallable {
            appDataBase.boardCategoryDao().insertBoardCategory(
                boardCategory
            )
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
            },{err-> Log.d("insertBoardCategory", err.toString())})
    }
    private fun updateBoardContents(boardContentsList:MutableList<BoardContents>){

        boardContentsListAdapter.updateBoardContents(boardContentsList)
    }
}