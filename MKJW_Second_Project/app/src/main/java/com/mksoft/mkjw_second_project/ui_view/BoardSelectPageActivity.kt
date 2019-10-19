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
import com.mksoft.mkjw_second_project.databinding.BoardSelectPageActivityBinding
import com.mksoft.mkjw_second_project.di.ViewModelFactory
import com.mksoft.mkjw_second_project.viewmodel.BoardSelectListViewModel
import com.mksoft.mkjw_second_project.viewmodel.RegisterCourseListViewModel

class BoardSelectPageActivity : AppCompatActivity() {

    private lateinit var binding: BoardSelectPageActivityBinding
    private lateinit var viewModel: BoardSelectListViewModel
    private var errorSnackbar: Snackbar? = null


    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.board_select_page_activity)
        binding.boardSelectPageActivityBoardListRecyclerView.layoutManager = LinearLayoutManager(this,  LinearLayoutManager.VERTICAL, false)
        viewModel = ViewModelProviders.of(this, ViewModelFactory()).get(BoardSelectListViewModel::class.java)

    }


}
