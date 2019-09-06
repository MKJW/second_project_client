package com.mksoft.mkjw_second_project.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.mksoft.mkjw_second_project.api.CourseAPI
import com.mksoft.mkjw_second_project.api.SectionAPI
import com.mksoft.mkjw_second_project.base.BaseViewModel
import com.mksoft.mkjw_second_project.model.Course.Student_Course
import com.mksoft.mkjw_second_project.model.DB.AppDataBase
import com.mksoft.mkjw_second_project.model.Section.Section
import com.mksoft.mkjw_second_project.utils.TEMP_GRADE
import com.mksoft.mkjw_second_project.utils.TEMP_SCHOOL_ID
import com.mksoft.mkjw_second_project.utils.TEMP_STUDENT_ID
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TimeTableViewModel : BaseViewModel() {
    @Inject
    lateinit var sectionAPI: SectionAPI
    @Inject
    lateinit var courseAPI:CourseAPI
    @Inject
    lateinit var appDataBase: AppDataBase

    private lateinit var subscription: Disposable

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val sectionID_courseNameMap : HashMap<String, Section> = HashMap()
    init {
        loadTimeTable()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadTimeTable() {
        subscription = Observable.fromCallable {
            appDataBase.sectionDao().getSections()//세션 불러오기
        }.concatMap {
            dbSectionList ->
            if(dbSectionList.isEmpty()){
                sectionAPI.getSections().concatMap { apiSectionList ->
                    appDataBase.sectionDao().insertSection(*apiSectionList.toTypedArray())
                    Observable.just(apiSectionList)

                }
            }else{
                Observable.just(dbSectionList)
            }
            //디비에 없으면 서버에 사용자가 수강하는 세션 요청
            //서버로부터 받은 세션 저장
            //1. 각각의 세션의 courseID를 통하여 course name불러오기
            //2. 각각의 세션의 해당하는 time_location을 불러오기
            //각각의 경우에 맞게 맵핑을 해주자.
        }.flatMap {//코스 이름 처리
            sectionList ->
            for(item in sectionList){
                mappingCourseNameSection(item)

            }
            Observable.just(sectionList)

        }.concatMap{//세션 시간 처리
            sectionList ->
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { startLoadSection() }
            .doOnTerminate { finishLoadSection() }
            .subscribe(
                { result -> result },//성공시 처리...여기에는 time_location 배열을 받고
                //배열을 긁으면서 데이터 바인딩을 해주자.
                { }//실패 처리
            )
    }
    //필요한 함수 정리


    //맵에다가 Map<세션 아이디, 세션>, Map<코스 네임, 시간위치클레스> 화면에 시간표 클릭시 코스네임을 통하여 시간위치 클레스에 접근하고
    //여기서 얻은 세션 아이디를 통하여 세션에 접근하고 그리고 거기에 대한 정보를 리턴해서 화면에 보여주자.

    //세션함수 : 세션리스트를 불러오는 함수


    //위치 시간 함수 : 세션 아이디를 넘겨주면 이에 맞는 위치 시간 리스트를 받는 함수



    private fun mappingCourseNameSection(section:Section){
        //
        subscription = Observable.fromCallable {
            appDataBase.courseDao().getCourse(
                section.course_id
            )
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                dbCourse->
                sectionID_courseNameMap[dbCourse.course_name]= section//??코틀린 맵 사용은???...
            }
    }

    private fun startLoadSection() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun finishLoadSection() {
        loadingVisibility.value = View.GONE
    }
}