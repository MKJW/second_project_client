package com.mksoft.mkjw_second_project.service

import android.util.Log
import com.mksoft.mkjw_second_project.api.FCMAPI
import com.mksoft.mkjw_second_project.base.BaseViewModel
import com.mksoft.mkjw_second_project.model.Board.BoardCategory
import com.mksoft.mkjw_second_project.model.DB.AppDataBase
import com.mksoft.mkjw_second_project.ui_view.CourseListAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FCMServiceBindingModel : BaseViewModel() {

    @Inject
    lateinit var fcmapi: FCMAPI
    @Inject
    lateinit var appDataBase: AppDataBase

    private lateinit var subscription: Disposable

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    fun sendToken(token: String) {
        subscription = fcmapi.sendFCMToken(token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> Log.d("token is successful?:", result.toString()) }
                , {})

    }
    //메시지 분류에 따라서 내부 디비에 카운트를 갱신하자

    fun addBoardategory(boardCategory: String) {
        subscription = Observable.fromCallable {
            appDataBase.boardCategoryDao().getCategory(
                boardCategory
            )
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                var notYetReadContentsCount = 0
                if (result.isEmpty()) {
                    //내부에 카테고리가 저장되어 있지 않은 경우
                    notYetReadContentsCount = 1
                } else {
                    //내부에 카테고리가 저장되어 있는 경우
                    notYetReadContentsCount = result[0].notYetReadContentsCount!!+1

                }
                insertBoardCategory(BoardCategory(boardCategory, notYetReadContentsCount))


            }, { err -> Log.d("insertBoardCategory", err.toString()) })
    }
    private fun insertBoardCategory(boardCategory: BoardCategory) {
        subscription = Observable.fromCallable {
            appDataBase.boardCategoryDao().insertBoardCategory(
                boardCategory
            )
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
            },
                { err -> Log.d("insertBoardCategory", err.toString()) })
    }


}