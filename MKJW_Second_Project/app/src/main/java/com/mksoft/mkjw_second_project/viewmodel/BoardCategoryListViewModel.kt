package com.mksoft.mkjw_second_project.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.mksoft.mkjw_second_project.base.BaseViewModel
import com.mksoft.mkjw_second_project.model.Board.BoardCategory
import com.mksoft.mkjw_second_project.model.Board.BoardCategoryContents
import com.mksoft.mkjw_second_project.model.Board.BoardContents
import com.mksoft.mkjw_second_project.model.DB.AppDataBase
import com.mksoft.mkjw_second_project.ui_view.BoardCategoryListAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BoardCategoryListViewModel : BaseViewModel(){

    @Inject
    lateinit var appDataBase: AppDataBase


    //여기서 해줘야 할 일은 게시판의 세부 사항(공지사항, 성저)을 나누는 카테고리를 보여주면서
    //동시에 읽지 않은 개수에 대하여 바인딩하여 페이지에 보여주는 역할을 한다.
    //여기서 내부에 저장되어 있는 카테고리를 불러오고 어뎁터에 넘겨주자.
    val boardCategoryListAdapter: BoardCategoryListAdapter = BoardCategoryListAdapter()
    private lateinit var subscription: Disposable
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    private val notYetReadContentsCountHashMap:HashMap<String, Int> = hashMapOf()
    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
    init {
        //initCategoryList()
        testMakeBoardList()
    }
    private fun testMakeBoardList(){
        val boardContentsTest1 = BoardContents("명기","수학 수행평가 3.3", "test")
        val boardContentsTest2 = BoardContents("명기", "수학 120쪽부터 139쪽까지")
        val boardContentsTestList = mutableListOf<BoardContents>()
        boardContentsTestList.add(boardContentsTest1)
        boardContentsTestList.add(boardContentsTest2)
        val boardCategory = BoardCategory("수학", 2)
        val boardCategoryContents = BoardCategoryContents(boardCategory, boardContentsTestList)
        val boardCategoryContentsList = mutableListOf<BoardCategoryContents>()
        boardCategoryContentsList.add(boardCategoryContents)

        successLoadBoardCategoryList(boardCategoryContentsList)
    }
    fun initCategoryList(){
        subscription = Observable.fromCallable{
            appDataBase.boardCategoryDao().getCategory()
        }.concatMap { dbCategoryList ->
                Observable.just(dbCategoryList)

        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { startRequestBoardCategory() }
            .doOnTerminate { finishRequestBoardCategory() }

            .subscribe(
                { result -> makeNotYetReadContentsCountHashMap(result) },
                {  }
            )

    }
    private fun makeNotYetReadContentsCountHashMap(categoryList:List<BoardCategory>){

        for(categoryItem in categoryList){
            notYetReadContentsCountHashMap[categoryItem.categoryName!!] = categoryItem.notYetReadContentsCount!!
        }
        //..여기서 api호출->그다음 테이블을 이용하여 읽지 않은 상태에 대하여 보드카테고리 리스트를 다시 만들어 주고 이를 갱신
    }
    //push로 받은 정보들을 내부에 저장하고 화면에 보여주기 전에 내부에 저장되어 있는 읽지 않은 상태를 불러오기(successLoadBoardCategoryList 호출출)
    //일단 이 로직은 매번 게시판 정보들을 불러오는 방식...
    //좀 더 좋은 방법은?..
    //흠..
    private fun successLoadBoardCategoryList(boardCategoryList: List<BoardCategoryContents>) {
        boardCategoryListAdapter.updateBoardCategory(boardCategoryList)
    }

    private fun insertBoardCategory(boardCategory: BoardCategory) {
        subscription = Observable.fromCallable {
            appDataBase.boardCategoryDao().insertBoardCategory(
                boardCategory
            )
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
            }
    }//여기서 카테고리를 만들어줄 이유는 없는 듯 없으면 없는 그대로

    private fun startRequestBoardCategory() {
        loadingVisibility.value = View.VISIBLE
    }

    private fun finishRequestBoardCategory() {
        loadingVisibility.value = View.GONE
    }

}