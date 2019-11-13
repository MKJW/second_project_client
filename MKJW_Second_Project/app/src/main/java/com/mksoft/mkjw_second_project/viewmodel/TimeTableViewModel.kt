package com.mksoft.mkjw_second_project.viewmodel

import android.util.Log
import android.view.View
import androidx.collection.ArrayMap
import androidx.lifecycle.MutableLiveData
import com.mksoft.mkjw_second_project.api.CourseAPI
import com.mksoft.mkjw_second_project.api.SectionAPI
import com.mksoft.mkjw_second_project.base.BaseViewModel
import com.mksoft.mkjw_second_project.model.DB.AppDataBase
import com.mksoft.mkjw_second_project.model.Section.Section
import com.mksoft.mkjw_second_project.model.Section.TimeLocation
import com.mksoft.mkjw_second_project.utils.TEMP_GRADE
import com.mksoft.mkjw_second_project.utils.TEMP_SCHOOL_ID
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import kotlin.collections.HashMap

class TimeTableViewModel : BaseViewModel() {
    @Inject
    lateinit var sectionAPI: SectionAPI
    @Inject
    lateinit var courseAPI: CourseAPI
    @Inject
    lateinit var appDataBase: AppDataBase

    private lateinit var subscription: Disposable

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val tableHeightForBinding:MutableList<MutableList<MutableLiveData<Int>>> = initHeightTable()
    val tableNameForBinding:MutableList<MutableList<MutableLiveData<String>>> = initNameTable()

    private val courseNameSection: ArrayMap<String, Section> = ArrayMap()//눌렀을 때 정보 받아오기
    private val sectionIDCourseName: ArrayMap<String, String> = ArrayMap()
    //시간표를 만들때 time_location에는 sectionID가 들어있으니 이에 맞는 코스 이름을 반환 받기 위해서
    private val totalClassCnt = 31

    private val dayToNumberTable: ArrayMap<String, Int> = initDayToNumberTable()

    private var bindedTimeTableCount: Int = 0

    init {
        //finishLoadTimeTable()
        testMakeTable()

    }
    private fun initDayToNumberTable():ArrayMap<String, Int>{
        val initDayToNumberTable:ArrayMap<String, Int> = ArrayMap()
        initDayToNumberTable["MON"] = 1
        initDayToNumberTable["TUE"] = 2
        initDayToNumberTable["WED"] = 3
        initDayToNumberTable["THU"] = 4
        initDayToNumberTable["FRI"] = 5
        return initDayToNumberTable
    }
    private fun initNameTable():MutableList<MutableList<MutableLiveData<String>>>{
        val initCurrentNameTable = mutableListOf<MutableList<MutableLiveData<String>>>()
        for(i in 0 .. 5){
            var initNameTable = mutableListOf<MutableLiveData<String>>()
            for(j in 0 .. 7){
                initNameTable.add(MutableLiveData())
            }
            initCurrentNameTable.add(initNameTable)

        }
        return initCurrentNameTable
    }
    private fun initHeightTable():MutableList<MutableList<MutableLiveData<Int>>>{
        val initCurrentHeightTable = mutableListOf<MutableList<MutableLiveData<Int>>>()
        for(i in 0 .. 5){
            var initHeightTable = mutableListOf<MutableLiveData<Int>>()
            for(j in 0 .. 7){
                initHeightTable.add(MutableLiveData())
            }
            initCurrentHeightTable.add(initHeightTable)

        }
        return initCurrentHeightTable
    }
    private fun testMakeTable(){
        sectionIDCourseName["1"] = "국어"
        val timeLocation1 = TimeLocation("MON", "1", "2", "","1")
        val timeLocation2 = TimeLocation("WED", "1", "2", "","1")
        val tempList1 = listOf<TimeLocation>(timeLocation1, timeLocation2)
        initTimeTable(tempList1)
        sectionIDCourseName["2"] = "화학"
        val timeLocation3 = TimeLocation("TUE", "1", "2", "","2")
        val timeLocation4 = TimeLocation("THU", "1", "2", "","2")
        val tempList2 = listOf<TimeLocation>(timeLocation3, timeLocation4)
        initTimeTable(tempList2)
        sectionIDCourseName["3"] = "수학"
        val timeLocation5 = TimeLocation("MON", "3", "4", "","3")
        val timeLocation6 = TimeLocation("TUE", "3", "4", "","3")
        val timeLocation7 = TimeLocation("WED", "3", "5", "","3")
        val tempList3 = listOf<TimeLocation>(timeLocation5, timeLocation6, timeLocation7)
        initTimeTable(tempList3)
        sectionIDCourseName["4"] = "컴퓨터"
        val timeLocation8 = TimeLocation("TUE", "5", "6", "","4")
        val timeLocation9 = TimeLocation("THU", "3", "4", "","4")
        val tempList4 = listOf<TimeLocation>(timeLocation8, timeLocation9)
        initTimeTable(tempList4)
        sectionIDCourseName["5"] = "물리"
        val timeLocation10 = TimeLocation("MON", "5", "6", "","5")
        val timeLocation11 = TimeLocation("THU", "5", "6", "","5")
        val tempList5 = listOf<TimeLocation>(timeLocation10, timeLocation11)
        initTimeTable(tempList5)
        sectionIDCourseName["6"] = "음악"
        val timeLocation12 = TimeLocation("FRI", "1", "3", "","6")
        val tempList6 = listOf<TimeLocation>(timeLocation12)
        initTimeTable(tempList6)
        sectionIDCourseName["7"] = "체육"
        val timeLocation13 = TimeLocation("FRI", "4", "6", "","7")
        val tempList7 = listOf<TimeLocation>(timeLocation13)
        initTimeTable(tempList7)
        sectionIDCourseName["8"] = "사회"
        val timeLocation14 = TimeLocation("WED", "6", "7", "","8")
        val tempList8 = listOf<TimeLocation>(timeLocation14)
        initTimeTable(tempList8)
    }

    override fun onCleared() {
        super.onCleared()
        //subscription.dispose()
    }

    private fun loadTimeTable() {
        startLoadTimeTable()//시작 할 때 바인딩되는 시간표 숨기기
        subscription = Observable.fromCallable {
            appDataBase.sectionDao().getSections()//세션 불러오기
        }.concatMap { dbSectionList ->
            if (dbSectionList.isEmpty()) {
                sectionAPI.getSections().concatMap { apiSectionList ->
                    appDataBase.sectionDao().insertSection(*apiSectionList.toTypedArray())
                    Observable.just(apiSectionList)

                }
            } else {
                Observable.just(dbSectionList)
            }
            //디비에 없으면 서버에 사용자가 수강하는 세션 요청
            //서버로부터 받은 세션 저장
            //1. 각각의 세션의 courseID를 통하여 course name불러오기
            //2. 각각의 세션의 해당하는 time_location을 불러오기
            //각각의 경우에 맞게 맵핑을 해주자.
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { sectionList ->
                    mappingCourseNameSection(sectionList)
                },
                { }//실패 처리
            )
    }
    //필요한 함수 정리


    private fun mappingCourseNameSection(sections: List<Section>) {
        //
        for (section in sections) {
            subscription = Observable.fromCallable {
                appDataBase.courseDao().getCourse(section.course_id)
            }.concatMap { dbCourse ->
                if (dbCourse == null) {
                    courseAPI.getCourses(TEMP_SCHOOL_ID, TEMP_GRADE).concatMap { apiCourseList ->
                        appDataBase.courseDao()
                            .insertCourse(*apiCourseList.toTypedArray())//어차피 지금 학년에서 듣는 코스 중에 자신의 분반이 들어 있기 때문에 모든 코스를 불러오고 이를 삽입한 후에 그중에 필요한 코스네임을 쿼리를 줘서 찾자
                        Observable.just(appDataBase.courseDao().getCourse(section.course_id))
                    }
                    //이처리를 하는 이유는 코스가 없이 앱에서 세션을 불러오고 코스네임을 찾으려고 하는 경우가 있을 수 있기 때문이다.
                } else {
                    Observable.just(dbCourse)
                }
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { dbCourse ->
                    courseNameSection[dbCourse.course_name] = section//시간표 클릭시 세부사항(교사이름...) 같은걸 알려주기 위하여
                    sectionIDCourseName[section.section_id] = dbCourse.course_name//시간표 만들 때 사용
                    loadTimeLocation(section)
                }
        }
    }

    private fun loadTimeLocation(section: Section) {
        //여기서는 section에 해당하는 loadtime loaction을 불러오고 이를 다시
        subscription = Observable.fromCallable {
            appDataBase.timeLocationDao().getTimeLocation(section.section_id)
        }.concatMap { dbTimeLocations ->
            if (dbTimeLocations.isEmpty()) {
                sectionAPI.getTimeLocation().concatMap { apiTimeLocations ->
                    for(timeLocation in apiTimeLocations) {
                        appDataBase.timeLocationDao().insertTimeLocation(timeLocation)
                    }
                    Observable.just(apiTimeLocations)
                }
            } else {
                Observable.just(dbTimeLocations)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ timeLocations ->
                initTimeTable(timeLocations)
            },
                {err-> Log.d("loadTimeLocation", err.toString())})
    }

    private fun initTimeTable(timeLocations: List<TimeLocation>) {
        //여기서는 모든 맵핑이 끝나고 화면에 보이는 시간표 레이아웃을 만들어 주는 함수이다.
        //이때 세션아이디를 넘기면 코스네임을 받아서 그 이름으로 레이아웃을 바인딩해주자
        for (timeLocation in timeLocations) {
            val dayNumber = dayToNumberTable[timeLocation.day]
            tableHeightForBinding[dayNumber!!][timeLocation.start_time!!.toInt()].value =
                60 * (timeLocation.end_time!!.toInt() - timeLocation.start_time.toInt() + 1)
            tableNameForBinding[dayNumber!!][timeLocation.start_time.toInt()].value = sectionIDCourseName[timeLocation.section_id]

            for (sectionTime in timeLocation.start_time.toInt() + 1..timeLocation.end_time.toInt()) {
                tableHeightForBinding[dayNumber!!][sectionTime].value = 0
                tableNameForBinding[dayNumber!!][sectionTime].value = ""
            }//시작되는 위치부터 끝나는 위치까지의 높이 설정
            //이름 설정
            bindedTimeTableCount += (timeLocation.end_time.toInt() - timeLocation.start_time.toInt() + 1)

        }

        checkMakeTimeTableFinish()
        //바인딩을 각각 스레드에서 동시에 돌리기 때문에 끝날때 마다 모든 친구들이 바인딩이 완료되었는지 확인하는 함수를 호출
    }

    private fun checkMakeTimeTableFinish() {
        if (bindedTimeTableCount == totalClassCnt) {
            //여기서 다 채워진 벡터를 바인딩 쪽으로 넘기자.
//            tableNameForBinding = tableName
//            tableHeightForBinding = tableHeight
            finishLoadTimeTable()
        }
    }

    private fun startLoadTimeTable() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun finishLoadTimeTable() {
        loadingVisibility.value = View.GONE

    }






}