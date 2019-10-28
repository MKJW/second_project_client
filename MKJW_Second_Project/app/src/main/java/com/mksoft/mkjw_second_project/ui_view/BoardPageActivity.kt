package com.mksoft.mkjw_second_project.ui_view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mksoft.mkjw_second_project.R
import com.mksoft.mkjw_second_project.databinding.BoardPageActivityBinding
import com.mksoft.mkjw_second_project.di.ViewModelFactory
import com.mksoft.mkjw_second_project.viewmodel.BoardCategoryListViewModel

class BoardPageActivity : AppCompatActivity() {

    private lateinit var binding: BoardPageActivityBinding
    private lateinit var viewModel: BoardCategoryListViewModel


    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.board_page_activity)
        binding.boardPageActivityBoardListRecyclerView.layoutManager = LinearLayoutManager(this,  LinearLayoutManager.VERTICAL, false)
        viewModel = ViewModelProviders.of(this, ViewModelFactory()).get(BoardCategoryListViewModel::class.java)
        binding.viewModel = viewModel
    }


}
