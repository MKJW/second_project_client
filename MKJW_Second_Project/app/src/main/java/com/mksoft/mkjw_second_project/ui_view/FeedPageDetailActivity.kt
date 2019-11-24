package com.mksoft.mkjw_second_project.ui_view

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.mksoft.mkjw_second_project.App
import com.mksoft.mkjw_second_project.R
import com.mksoft.mkjw_second_project.databinding.FeedPageDetailActivityBinding
import com.mksoft.mkjw_second_project.di.ViewModelFactory
import com.mksoft.mkjw_second_project.viewmodel.FeedPageDetailViewModel
import kotlinx.android.synthetic.main.feed_page_detail_activity.*


class FeedPageDetailActivity : AppCompatActivity(){
    private lateinit var binding: FeedPageDetailActivityBinding
    private lateinit var viewModel: FeedPageDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.feed_page_detail_activity)
        viewModel = ViewModelProviders.of(this, ViewModelFactory()).get(FeedPageDetailViewModel::class.java)
        initViewModel()
        binding.viewModel = viewModel
        setSupportActionBar(feed_page_detail_activity_toolbar_Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = null
        //뒤로가기 버튼 만들기
    }


    fun initViewModel(){

        viewModel.bindFeedItem(this.intent.getBooleanExtra("currentClickState", false), this.intent.getIntExtra("currentStarNum", 0),this.intent.getStringExtra("imageSrc"))
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            android.R.id.home->{
                onBackPressed()
            }

        }
        return super.onOptionsItemSelected(item)
    }
}