package com.mksoft.mkjw_second_project.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.mksoft.mkjw_second_project.R
import com.mksoft.mkjw_second_project.api.RegisterCourseAPI
import com.mksoft.mkjw_second_project.model.Course.Course
import com.mksoft.mkjw_second_project.model.DB.AppDataBase
import com.mksoft.mkjw_second_project.base.BaseViewModel
import com.mksoft.mkjw_second_project.model.Course.Student_Course
import com.mksoft.mkjw_second_project.utils.TEMP_GRADE
import com.mksoft.mkjw_second_project.utils.TEMP_SCHOOL_ID
import com.mksoft.mkjw_second_project.utils.TEMP_STUDENT_ID
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RegisterCourseViewModel : BaseViewModel(){
    @Inject
    lateinit var registerCourseAPI: RegisterCourseAPI
    @Inject
    lateinit var appDataBase: AppDataBase
    val courseName = MutableLiveData<String>()
    var courseId: String = ""
    val buttonText:MutableLiveData<String> = MutableLiveData()
    val loadingVisibility:MutableLiveData<Int> = MutableLiveData()
    val buttonVisibility:MutableLiveData<Int> = MutableLiveData()
    val buttonBackGrond:MutableLiveData<Int> = MutableLiveData()
    val buttonClickState:MutableLiveData<Boolean> = MutableLiveData()//true : 서버로 insert 요청, false: delete요청
    private lateinit var subscription: Disposable

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    fun checkState(view: View){
        if(buttonClickState.value == true){
            requestRegisterCourse()
        }else{
            requestDeleteCourse()
        }
    }
    fun requestDeleteCourse(){
        subscription =Observable.fromCallable {
            registerCourseAPI.postUnregisterCourse(Student_Course(/*TEMP_SCHOOL_ID,*/ courseId, TEMP_STUDENT_ID))
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { startRequestRegisterCourse() }
            .doOnTerminate { finishrequestRegisterCourse() }
            .subscribe(
                {result -> sucessRequestDeleteCourse()},//result가 Observable이어야 한다.
                {failRequestCourse()}
            )
    }
    fun requestRegisterCourse(){
        //서버에 코스등록 요청
        subscription =Observable.fromCallable {
            registerCourseAPI.postRegisterCourse(Student_Course(/*TEMP_SCHOOL_ID,*/ courseId, TEMP_STUDENT_ID))
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { startRequestRegisterCourse() }
            .doOnTerminate { finishrequestRegisterCourse() }
            .subscribe(
                {result -> sucessRequestRegisterCourse()},//result가 Observable이어야 한다.
                {failRequestCourse()}//일단은 겹치는 수강으로 실패한 경우
            )

    }

    fun bind(course: Course){
        courseName.value = course.course_name
        courseId = course.course_id
        checkRequestCourse(course.course_id)
        buttonVisibility.value = View.VISIBLE
        loadingVisibility.value = View.GONE

    }

    fun deleteSuccessCourse(){
        subscription = Observable.fromCallable {
            appDataBase.student_courseDao().delete(
                Student_Course(/*TEMP_SCHOOL_ID, */courseId, TEMP_STUDENT_ID))
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
            }
    }

    fun insertSuccessCourse(){
        subscription = Observable.fromCallable {
            appDataBase.student_courseDao().insertAll(
                Student_Course(/*TEMP_SCHOOL_ID, */courseId, TEMP_STUDENT_ID))
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
            }
    }//성공한 과목에 대하여 디비에 저장

    fun checkRequestCourse(course_id:String){
        subscription = Observable.fromCallable {
            appDataBase.student_courseDao().getStudent_Course(course_id)
            //수강신청을 하는 도중에 앱을 삭제하고 다시 돌아온 경우에는 db에도 신청한 데이터가 날라가기 때문에
            //db에 없으면 server쪽으로 (student_id, course_id)를 넘겨서 이미 신청했는지를 알아볼 필요가 있어보인다.

        }.concatMap {
                dbCourse ->
            if(dbCourse == 0)
                registerCourseAPI.getCourse(Student_Course(course_id, TEMP_STUDENT_ID) ).concatMap {
                        apiCourse ->Observable.just(1)
                    if(apiCourse == "True"){
                        insertSuccessCourse()
                        Observable.just(1)
                    }else{
                        Observable.just(0)
                    }

                }
            else
                Observable.just(dbCourse)

        }
            .subscribeOn(Schedulers.io())//이걸 해줘야 UI lock이 풀린다.
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                if(result == 0){
                    buttonBackGrond.value = R.drawable.custom_white_button//요청을 하지 않은 경우
                    buttonText.value = "신청"
                    buttonClickState.value = true
                }else{

                    buttonBackGrond.value = R.drawable.checked//요청을 이미 한 코스
                    buttonText.value = ""
                    buttonClickState.value = false
                }

            }
    }//이미 신청을 한 과목인지 확인


    private fun startRequestRegisterCourse(){
        loadingVisibility.value = View.VISIBLE
        buttonVisibility.value = View.GONE
    }
    private fun finishrequestRegisterCourse(){
        loadingVisibility.value = View.GONE
        buttonVisibility.value = View.VISIBLE
    }

    private fun sucessRequestRegisterCourse(){
        buttonBackGrond.value = R.drawable.checked
        buttonText.value = ""
        buttonClickState.value = false
        insertSuccessCourse()
    }
    private fun sucessRequestDeleteCourse(){
        buttonBackGrond.value = R.drawable.custom_white_button
        buttonText.value = "신청"
        buttonClickState.value = true
        deleteSuccessCourse()
    }
    private fun errorRequestRegisterCourse(){
        buttonBackGrond.value = R.drawable.custom_white_button
        buttonText.value = "신청"

    }//서버 문제로 다시 신청해야하는 경우
    private fun failRequestCourse(){
        buttonBackGrond.value = R.drawable.custom_red_button
        buttonText.value = "실패"
    }//중국어 일본어 처럼 택1을 해야하는 경우 중복으로 신청하여 들을 수 없는 경우




}//어뎁터에서 아이템 뷰 별로의 바인딩