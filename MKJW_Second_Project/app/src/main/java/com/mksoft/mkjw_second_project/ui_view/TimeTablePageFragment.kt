package com.mksoft.mkjw_second_project.ui_view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mksoft.mkjw_second_project.R
import com.mksoft.mkjw_second_project.databinding.TimeTablePageFragmentBinding
import com.mksoft.mkjw_second_project.di.ViewModelFactory
import com.mksoft.mkjw_second_project.viewmodel.BoardCategoryListViewModel
import com.mksoft.mkjw_second_project.viewmodel.TimeTableViewModel

class TimeTablePageFragment : Fragment(){


    private var errorSnackbar: Snackbar? = null
    private lateinit var binding:TimeTablePageFragmentBinding
    private lateinit var viewModel: TimeTableViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.time_table_page_fragment, container,false)
        viewModel = ViewModelProviders.of(this, ViewModelFactory()).get(TimeTableViewModel::class.java)
        binding.viewModel = viewModel
        return binding.root

    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }
}