package com.mksoft.mkjw_second_project.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.mksoft.mkjw_second_project.R
import com.mksoft.mkjw_second_project.base.BaseViewModel
import com.mksoft.mkjw_second_project.model.Course.Course
import com.mksoft.mkjw_second_project.model.SNS.Feed
import com.mksoft.mkjw_second_project.ui_view.FeedListAdapter
import com.mksoft.mkjw_second_project.ui_view.FeedPageFragment

class FeedPageViewModel :BaseViewModel(){
    val feedListAdapter: FeedListAdapter = FeedListAdapter()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickLister = View.OnClickListener { //에러바 클릭시 처리
         }
    init {
        //feed 불러오기
        testInit()
    }
    fun updateFeedPageFragment(currentFeedPageFragment: FeedPageFragment){
        feedListAdapter.updateFeedPageFragment(currentFeedPageFragment)
    }
    private fun successLoadFeed(feedList:List<Feed>) {
        feedListAdapter.updateFeedList(feedList)
    }
    private fun startLoadFeed() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }
    private fun finishLoadFeed() {
        loadingVisibility.value = View.GONE
    }

    private fun failLoadfeed() {
        errorMessage.value = R.string.feed_load_error
    }
    private fun testInit(){
        val tempFeedList:MutableList<Feed> = mutableListOf()
        val tempFeed1 = Feed("1","https://img.siksinhot.com/place/1457946348861393.PNG?w=508&h=412&c=Y", 100, "123","1")

        val tempFeed2 = Feed("2","https://img.siksinhot.com/place/1463988124958100.png?w=508&h=412&c=Y", 101, "123","1")

        val tempFeed3 = Feed("3","https://img.siksinhot.com/place/1464585331273229.png?w=508&h=412&c=Y", 102, "123","1")

        val tempFeed4 = Feed("4","https://img.siksinhot.com/place/1530597402238010.jpg?w=508&h=412&c=Y", 103, "123","1")

        val tempFeed5 = Feed("5","https://img.siksinhot.com/place/1409714449233527.jpg?w=508&h=412&c=Y", 104, "123","1")

        val tempFeed6 = Feed("6","https://img.siksinhot.com/place/1459317297288889.jpg?w=508&h=412&c=Y", 11, "123","1")

        val tempFeed7 = Feed("7","https://img.siksinhot.com/place/1457689271606212.PNG?w=508&h=412&c=Y", 120, "123","1")

        tempFeedList.add(tempFeed1)

        tempFeedList.add(tempFeed2)

        tempFeedList.add(tempFeed3)

        tempFeedList.add(tempFeed4)

        tempFeedList.add(tempFeed5)

        tempFeedList.add(tempFeed6)

        tempFeedList.add(tempFeed7)
        successLoadFeed(tempFeedList)
    }

}