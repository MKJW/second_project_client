package com.mksoft.mkjw_second_project.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.mksoft.mkjw_second_project.base.BaseViewModel
import com.mksoft.mkjw_second_project.model.Board.BoardCategory
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
    var rootCategory:String = ""//현재 페이지의 상위 카테고리//분반 혹은 자신의 소속반
    private val currentCategoryList:List<String> = listOf("공지", "성적")
    private lateinit var subscription: Disposable
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
    fun initRootCategory(rootCategory:String){
        this.rootCategory = rootCategory
    }
    fun initCategoryList(){
        subscription = Observable.fromCallable{
            appDataBase.boardCategoryDao().getCategory(rootCategory)
        }.concatMap { dbCategoryList ->
            if (dbCategoryList.size != currentCategoryList.size){
                val sendBoardCategoryList:MutableList<BoardCategory> = mutableListOf()
                for(currentCategory in currentCategoryList){
                    var checkExist = false
                    for(dbCategoryListItem in dbCategoryList){
                        if(dbCategoryListItem.currentCategory == currentCategory){
                            checkExist = true
                            sendBoardCategoryList.add(dbCategoryListItem)
                            break
                        }
                    }
                    if(!checkExist){
                        val currentCategoryItem = BoardCategory(rootCategory, currentCategory, 1)
                        sendBoardCategoryList.add(currentCategoryItem)
                        insertBoardCategory(currentCategoryItem)
                    }

                }
                Observable.just(sendBoardCategoryList)
            }
            else
                Observable.just(dbCategoryList)

        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { startRequestBoardCategory() }
            .doOnTerminate { finishRequestBoardCategory() }

            .subscribe(
                { result -> successLoadBoardCategoryList(result) },
                {  }
            )

    }
    //순서에 대하여 고정이 필요할까? 흠...
    private fun successLoadBoardCategoryList(boardCategoryList: List<BoardCategory>) {
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
    }

    private fun startRequestBoardCategory() {
        loadingVisibility.value = View.VISIBLE
    }

    private fun finishRequestBoardCategory() {
        loadingVisibility.value = View.GONE
    }

}