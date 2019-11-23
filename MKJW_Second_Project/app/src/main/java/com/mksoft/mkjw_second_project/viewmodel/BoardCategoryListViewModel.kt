package com.mksoft.mkjw_second_project.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.collection.ArrayMap
import androidx.lifecycle.MutableLiveData
import com.mksoft.mkjw_second_project.api.BoardAPI
import com.mksoft.mkjw_second_project.base.BaseViewModel
import com.mksoft.mkjw_second_project.model.Board.BoardCategory
import com.mksoft.mkjw_second_project.model.Board.BoardCategoryContents
import com.mksoft.mkjw_second_project.model.Board.BoardContents

import com.mksoft.mkjw_second_project.model.DB.AppDataBase
import com.mksoft.mkjw_second_project.ui_view.BoardCategoryListAdapter
import com.mksoft.mkjw_second_project.utils.TEMP_STUDENT_ID

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class BoardCategoryListViewModel : BaseViewModel() {

    @Inject
    lateinit var appDataBase: AppDataBase
    @Inject
    lateinit var boardAPI: BoardAPI

    //여기서 해줘야 할 일은 게시판의 세부 사항(공지사항, 성저)을 나누는 카테고리를 보여주면서
    //동시에 읽지 않은 개수에 대하여 바인딩하여 페이지에 보여주는 역할을 한다.
    //여기서 내부에 저장되어 있는 카테고리를 불러오고 어뎁터에 넘겨주자.
    val boardCategoryListAdapter: BoardCategoryListAdapter = BoardCategoryListAdapter()
    //private lateinit var subscription: Disposable
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    private val notYetReadContentsCountMap: ArrayMap<String, Int> = ArrayMap()
    //게시판의 카테고리가 1000개 보다는 작을 것이라고 생각
//    override fun onCleared() {
//        super.onCleared()
//        subscription.dispose()
//    }

    init {
        initCategoryList()
        //testMakeBoardList()
    }

    private fun testMakeBoardList() {
    }

    @SuppressLint("CheckResult")
    fun initCategoryList() {
        Observable.fromCallable {
            appDataBase.boardCategoryDao().getAllCategory()
        }.concatMap { dbCategoryList ->
            Observable.just(dbCategoryList)

        }

            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { startRequestBoardCategory() }
            .subscribe(
                { result -> makeNotYetReadContentsCountMap(result) },
                { err -> Log.d("initCategoryList", err.toString()) }
            )

    }

    private fun makeNotYetReadContentsCountMap(categoryList: List<BoardCategory>) {
        for (categoryItem in categoryList) {
            notYetReadContentsCountMap[categoryItem.categoryName!!] =
                categoryItem.notYetReadContentsCount!!
        }
        loadBoardContentsList()
        //..여기서 api호출->그다음 테이블을 이용하여 읽지 않은 상태에 대하여 보드카테고리 리스트를 다시 만들어 주고 이를 갱신
    }

    //push로 받은 정보들을 내부에 저장하고 화면에 보여주기 전에 내부에 저장되어 있는 읽지 않은 상태를 불러오기(successLoadBoardCategoryList 호출출)
    //일단 이 로직은 매번 게시판 정보들을 불러오는 방식...
    //좀 더 좋은 방법은?..
    //흠..
    @SuppressLint("CheckResult")
    private fun loadBoardContentsList() {

        boardAPI.getAllNotice(TEMP_STUDENT_ID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate { finishRequestBoardCategory() }
            .subscribe({ result ->
                Log.d("test1111", Date(result[0].createdDate).toString())
                makeBoardCategoryContents(result)
            }
                , { err -> Log.d("test1111", err.toString()) })

    }

    private fun makeBoardCategoryContents(boardContentsList: List<BoardContents>) {
        val boardCategoryContentsList = mutableListOf<BoardCategoryContents>()
        val hashCategoryContents:ArrayMap<String, MutableList<BoardContents>> = ArrayMap()
        val currentCategoryList:MutableList<String> = mutableListOf()//지금까지 받은 카테고리

        for (boardContentsItem in boardContentsList) {
            if(hashCategoryContents[boardContentsItem.category?:"test"] == null){
                hashCategoryContents[boardContentsItem.category?:"test"]= mutableListOf(boardContentsItem)
                currentCategoryList.add(boardContentsItem.category ?:"test")
            }else{
                hashCategoryContents[boardContentsItem.category?:"test"]!!.add(boardContentsItem)
            }
        }
        for(categoryListItem in currentCategoryList){
            if(notYetReadContentsCountMap[categoryListItem] == null){
                val boardCategory = BoardCategory(categoryListItem, 0)
                boardCategoryContentsList.add(BoardCategoryContents(boardCategory, hashCategoryContents[categoryListItem]!!))

            }else{
                val boardCategory = BoardCategory(categoryListItem, notYetReadContentsCountMap[categoryListItem])
                boardCategoryContentsList.add(BoardCategoryContents(boardCategory, hashCategoryContents[categoryListItem]!!))
            }
        }
        successLoadBoardCategoryList(boardCategoryContentsList)
    }

    private fun successLoadBoardCategoryList(boardCategoryContentsList: List<BoardCategoryContents>) {
        boardCategoryListAdapter.updateBoardCategory(boardCategoryContentsList)
    }

    @SuppressLint("CheckResult")
    private fun insertBoardCategory(boardCategory: BoardCategory) {
        Observable.fromCallable {
            appDataBase.boardCategoryDao().insertBoardCategory(
                boardCategory
            )
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
            },
                { err -> Log.d("insertBoardCategory", err.toString()) })
    }//여기서 카테고리를 만들어줄 이유는 없는 듯 없으면 없는 그대로

    private fun startRequestBoardCategory() {
        loadingVisibility.value = View.VISIBLE
    }

    private fun finishRequestBoardCategory() {
        loadingVisibility.value = View.GONE
    }

}