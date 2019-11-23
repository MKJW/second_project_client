package com.mksoft.mkjw_second_project.ui_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mksoft.mkjw_second_project.App
import com.mksoft.mkjw_second_project.R
import com.mksoft.mkjw_second_project.databinding.BoardPageFragmentBinding
import com.mksoft.mkjw_second_project.databinding.FeedPageFragmentBinding
import com.mksoft.mkjw_second_project.di.ViewModelFactory
import com.mksoft.mkjw_second_project.utils.ProductGridItemDecoration
import com.mksoft.mkjw_second_project.viewmodel.BoardCategoryListViewModel
import com.mksoft.mkjw_second_project.viewmodel.FeedPageViewModel

class FeedPageFragment : Fragment(){
    private lateinit var binding: FeedPageFragmentBinding
    private lateinit var viewModel: FeedPageViewModel
    private var errorSnackbar: Snackbar? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.feed_page_fragment, container,false)
        binding.feedPageFragmentFeedListRecyclerView.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(App.applicationContext(), 2, RecyclerView.HORIZONTAL, false)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position % 3 == 2) 2 else 1
            }
        }
        binding.feedPageFragmentFeedListRecyclerView.layoutManager = gridLayoutManager
        val largePadding = resources.getDimensionPixelSize(R.dimen.shr_staggered_product_grid_spacing_large)
        val smallPadding = resources.getDimensionPixelSize(R.dimen.shr_staggered_product_grid_spacing_small)
        binding.feedPageFragmentFeedListRecyclerView.addItemDecoration(ProductGridItemDecoration(largePadding, smallPadding))
        viewModel = ViewModelProviders.of(this.activity!!, ViewModelFactory()).get(
            FeedPageViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel
        return binding.root

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