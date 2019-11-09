package com.mksoft.mkjw_second_project.ui_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mksoft.mkjw_second_project.App
import com.mksoft.mkjw_second_project.R
import com.mksoft.mkjw_second_project.databinding.BoardPageFragmentBinding
import com.mksoft.mkjw_second_project.di.ViewModelFactory
import com.mksoft.mkjw_second_project.viewmodel.BoardCategoryListViewModel
import kotlinx.android.synthetic.main.board_page_fragment.*

class BoardPageFragment : Fragment() {

    private lateinit var binding: BoardPageFragmentBinding
    private lateinit var viewModel: BoardCategoryListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.board_page_fragment, container,false)
        val linearLayoutManager = LinearLayoutManager(this.activity!!,  LinearLayoutManager.VERTICAL, false)
        binding.boardPageFragmentBoardListRecyclerView.layoutManager = linearLayoutManager
        binding.boardPageFragmentBoardListRecyclerView.setHasFixedSize(true)
        viewModel = ViewModelProviders.of(this.activity!!, ViewModelFactory()).get(BoardCategoryListViewModel::class.java)
        //this.activity!!해야지 다시 viewmodel을 만들지 않는다.
        binding.viewModel = viewModel
        return binding.root

    }

}
