package com.mksoft.mkjw_second_project.ui_view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mksoft.mkjw_second_project.R
import com.mksoft.mkjw_second_project.databinding.BoardCategoryPageActivityBinding
import com.mksoft.mkjw_second_project.di.ViewModelFactory
import com.mksoft.mkjw_second_project.viewmodel.BoardCategoryListViewModel

class BoardCategoryPageActivity : AppCompatActivity() {

    private lateinit var binding: BoardCategoryPageActivityBinding
    private lateinit var viewModel: BoardCategoryListViewModel


    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.board_category_page_activity)
        binding.boardCategoryPageActivityBoardListRecyclerView.layoutManager = LinearLayoutManager(this,  LinearLayoutManager.VERTICAL, false)
        viewModel = ViewModelProviders.of(this, ViewModelFactory()).get(BoardCategoryListViewModel::class.java)
        viewModel.initRootCategory("3-5")
        viewModel.initCategoryList()
        binding.viewModel = viewModel
    }


}
