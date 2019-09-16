package com.mksoft.mkjw_second_project.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.mksoft.mkjw_second_project.R
import com.mksoft.mkjw_second_project.api.CourseAPI
import com.mksoft.mkjw_second_project.model.Course.Course
import com.mksoft.mkjw_second_project.model.DB.AppDataBase
import com.mksoft.mkjw_second_project.base.BaseViewModel
import com.mksoft.mkjw_second_project.model.Course.StudentCourse
import com.mksoft.mkjw_second_project.utils.TEMP_STUDENT_ID
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RegisterCourseViewModel : BaseViewModel() {
    @Inject
    lateinit var registerCourseAPI: CourseAPI
    @Inject
    lateinit var appDataBase: AppDataBase
    val courseName = MutableLiveData<String>()
    var courseId: String = ""
    val buttonText: MutableLiveData<String> = MutableLiveData()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val buttonVisibility: MutableLiveData<Int> = MutableLiveData()
    val buttonBackGrond: MutableLiveData<Int> = MutableLiveData()
    private val buttonClickState: MutableLiveData<Boolean> = MutableLiveData()//true : 서버로 insert 요청, false: delete요청
    val buttonWidth: MutableLiveData<Int> = MutableLiveData()
    private lateinit var subscription: Disposable


    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    fun checkState(view: View) {
        if (buttonClickState.value == true) {
            requestRegisterCourse()
        } else {
            requestDeleteCourse()
        }
    }

    private fun requestDeleteCourse() {
        subscription = registerCourseAPI.postUnregisterCourse(/*TEMP_SCHOOL_ID,*/ courseId, TEMP_STUDENT_ID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { startRequestCourse() }
            .doOnTerminate { finishrequestCourse() }
            .subscribe(
                { result -> initBeforeRegisteredCourseState(true) },//result가 Observable이어야 한다.
                { failRequestCourse() }
            )
    }

    private fun requestRegisterCourse() {
        //서버에 코스등록 요청
        subscription = registerCourseAPI.postRegisterCourse(/*TEMP_SCHOOL_ID,*/ courseId, TEMP_STUDENT_ID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { startRequestCourse() }
            .doOnTerminate { finishrequestCourse() }
            .subscribe(
                { result -> initRegisteredCourseState(true) },//result가 Observable이어야 한다.
                { failRequestCourse() }//일단은 겹치는 수강으로 실패한 경우
            )

    }

    fun bind(course: Course) {
        courseName.value = course.course_name
        courseId = course.course_id
        checkRequestCourse(courseId)


    }

    private fun deleteSuccessCourse() {
        subscription = Observable.fromCallable {
            appDataBase.studentCourseDao().delete(
                StudentCourse(/*TEMP_SCHOOL_ID, */courseId, TEMP_STUDENT_ID)
            )
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
            }
    }

    private fun insertSuccessCourse() {
        subscription = Observable.fromCallable {
            appDataBase.studentCourseDao().insertStudentCourse(
                StudentCourse(/*TEMP_SCHOOL_ID, */courseId, TEMP_STUDENT_ID)
            )
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
            }
    }//성공한 과목에 대하여 디비에 저장

    private fun checkRequestCourse(course_id: String) {
        subscription = Observable.fromCallable {
            appDataBase.studentCourseDao().getStudentCourse(course_id)
            //수강신청을 하는 도중에 앱을 삭제하고 다시 돌아온 경우에는 db에도 신청한 데이터가 날라가기 때문에
            //db에 없으면 server쪽으로 (student_id, course_id)를 넘겨서 이미 신청했는지를 알아볼 필요가 있어보인다.

        }.concatMap { dbCourse ->
            if (dbCourse == 0) {
                registerCourseAPI.checkCourse(course_id, TEMP_STUDENT_ID).concatMap { apiCourse ->
                    if (apiCourse) {
                        insertSuccessCourse()
                        Observable.just(1)
                    } else {
                        Observable.just(0)
                    }//반환 받는 타입을 확인해보자...


                }
            } else
                Observable.just(dbCourse)

        }
            .subscribeOn(Schedulers.io())//이걸 해줘야 UI lock이 풀린다.
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { startRequestCourse() }
            .doOnTerminate { finishrequestCourse() }
            .subscribe { result ->
                if (result == 0) {
                    initBeforeRegisteredCourseState(false)
                } else {
                    initRegisteredCourseState(false)
                }

            }
    }//이미 신청을 한 과목인지 확인

    private fun startRequestCourse() {
        loadingVisibility.value = View.VISIBLE
        buttonVisibility.value = View.GONE
    }

    private fun finishrequestCourse() {
        loadingVisibility.value = View.GONE
        buttonVisibility.value = View.VISIBLE
    }

    private fun initRegisteredCourseState(requestState: Boolean) {
        buttonBackGrond.value = R.drawable.checked
        buttonText.value = ""
        buttonClickState.value = false
        buttonWidth.value = 40
        if (requestState)
            insertSuccessCourse()
    }//requsetState : 요청해서 코스 상태를 갱신한 경우 디비에 넣어주자

    private fun initBeforeRegisteredCourseState(requestState: Boolean) {
        buttonBackGrond.value = R.drawable.custom_white_button
        buttonText.value = "신청"
        buttonClickState.value = true
        buttonWidth.value = 80
        if (requestState)
            deleteSuccessCourse()
    }//requsetState : 요청해서 코스 상태를 갱신한 경우 디비에 넣어주자

    private fun errorRequestRegisterCourse() {
        buttonBackGrond.value = R.drawable.custom_white_button
        buttonText.value = "신청"
        buttonWidth.value = 80
    }//서버 문제로 다시 신청해야하는 경우 ... 이 함수가 필요할 때 initBeforeRegisteredCourseState와 합쳐주자.

    private fun failRequestCourse() {
        buttonBackGrond.value = R.drawable.custom_red_button
        buttonText.value = "실패"
        buttonWidth.value = 80
    }//중국어 일본어 처럼 택1을 해야하는 경우 중복으로 신청하여 들을 수 없는 경우


}//어뎁터에서 아이템 뷰 별로의 바인딩