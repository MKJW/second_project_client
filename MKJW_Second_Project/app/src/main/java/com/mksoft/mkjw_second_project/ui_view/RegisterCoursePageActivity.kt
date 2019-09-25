package com.mksoft.mkjw_second_project.ui_view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mksoft.mkjw_second_project.di.ViewModelFactory
import com.mksoft.mkjw_second_project.R
import com.mksoft.mkjw_second_project.viewmodel.RegisterCourseListViewModel
import com.mksoft.mkjw_second_project.databinding.RegisterCoursesPageActivityBinding

class RegisterCoursePageActivity : AppCompatActivity() {

    private lateinit var binding: RegisterCoursesPageActivityBinding
    private lateinit var viewModel: RegisterCourseListViewModel
    private var errorSnackbar: Snackbar? = null


    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.register_courses_page_activity)
        binding = DataBindingUtil.setContentView(this, R.layout.register_courses_page_activity)
        binding.registerCoursesPageActivityRegisterCourseRecyclerView.layoutManager = LinearLayoutManager(this,  LinearLayoutManager.VERTICAL, false)
        viewModel = ViewModelProviders.of(this, ViewModelFactory()).get(RegisterCourseListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })
        //MutableLiveData을 쓰는 방법은 옵저브(주인 엑티비티, 변경시 변경된 값 바인딩해주는 옵저버)
        binding.viewModel = viewModel//바인딩시 경로 페키지가 겹쳐서 에러...ViewModel이라는 페키 이름 사용

    }

    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickLister)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }
}
