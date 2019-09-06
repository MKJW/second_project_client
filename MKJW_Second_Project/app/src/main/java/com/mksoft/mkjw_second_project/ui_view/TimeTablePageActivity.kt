package com.mksoft.mkjw_second_project.ui_view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mksoft.mkjw_second_project.R
import com.mksoft.mkjw_second_project.di.ViewModelFactory
import com.mksoft.mkjw_second_project.viewmodel.RegisterCourseListViewModel

class TimeTablePageActivity : AppCompatActivity(){


    private var errorSnackbar: Snackbar? = null

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.time_table_page_activity)


    }


    private fun hideError(){
        errorSnackbar?.dismiss()
    }
}