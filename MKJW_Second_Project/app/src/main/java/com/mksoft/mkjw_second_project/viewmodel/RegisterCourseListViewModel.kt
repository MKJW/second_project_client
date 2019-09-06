package com.mksoft.mkjw_second_project.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.mksoft.mkjw_second_project.api.CourseAPI
import com.mksoft.mkjw_second_project.model.Course.Course
import com.mksoft.mkjw_second_project.model.DB.AppDataBase
import com.mksoft.mkjw_second_project.R
import com.mksoft.mkjw_second_project.ui_view.CourseListAdapter
import com.mksoft.mkjw_second_project.base.BaseViewModel
import com.mksoft.mkjw_second_project.utils.TEMP_GRADE
import com.mksoft.mkjw_second_project.utils.TEMP_SCHOOL_ID
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RegisterCourseListViewModel : BaseViewModel() {

    @Inject
    lateinit var registerCourseAPI: CourseAPI
    @Inject
    lateinit var appDataBase: AppDataBase

    val courseListAdapter: CourseListAdapter = CourseListAdapter()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickLister = View.OnClickListener { loadCourses() }
    //실패했을 때 클릭하면 다시 불러오기

    private lateinit var subscription: Disposable

    init {
        loadCourses()
    }
    //뷰모델이 만들어 지면은 바론 한번 코스 받아오기

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }//뷰모델이 죽을 때 할당된 변수들 삭제

    private fun loadCourses() {
        subscription = Observable.fromCallable {
            appDataBase.courseDao().getCourses()
        }.concatMap { dbCourseList ->
            if (dbCourseList.isEmpty())
                registerCourseAPI.getCourses(TEMP_SCHOOL_ID, TEMP_GRADE).concatMap { apiCourseList ->
                    appDataBase.courseDao().insertCourse(*apiCourseList.toTypedArray())//받은 리스트에서 널을 처리해주기 위해서
                    Observable.just(apiCourseList)
                }
            else
                Observable.just(dbCourseList)

        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { startLoadCourse() }
            .doOnTerminate { finishLoadCourse() }
            .subscribe(
                { result -> successLoadCourse(result) },
                { failLoadCourse() }
            )
    }


    private fun startLoadCourse() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun finishLoadCourse() {
        loadingVisibility.value = View.GONE
    }

    private fun successLoadCourse(courseList: List<Course>) {
        courseListAdapter.updateCourseList(courseList)
    }

    private fun failLoadCourse() {
        errorMessage.value = R.string.course_load_error
    }

}//리사이클러뷰에 리스트 바인딩


