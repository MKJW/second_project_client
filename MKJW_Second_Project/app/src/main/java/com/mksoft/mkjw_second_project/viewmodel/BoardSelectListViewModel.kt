package com.mksoft.mkjw_second_project.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.mksoft.mkjw_second_project.R
import com.mksoft.mkjw_second_project.api.CourseAPI
import com.mksoft.mkjw_second_project.base.BaseViewModel
import com.mksoft.mkjw_second_project.model.Course.Course
import com.mksoft.mkjw_second_project.model.DB.AppDataBase
import com.mksoft.mkjw_second_project.ui_view.CourseListAdapter
import com.mksoft.mkjw_second_project.utils.TEMP_GRADE
import com.mksoft.mkjw_second_project.utils.TEMP_SCHOOL_ID
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BoardSelectListViewModel : BaseViewModel(){

    //여기서 해줘야 할 일은 게시판의 세부 사항(공지사항, 성저)을 나누는 카테고리를 보여주면서
    //동시에 읽지 않은 개수에 대하여 바인딩하여 페이지에 보여주는 역할을 한다.

    val courseListAdapter: CourseListAdapter = CourseListAdapter()


    init {

    }






}