package com.mksoft.mkjw_second_project.ui_view

import android.content.Intent
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
        initViewModel()
        toolbarInit()
        initLayout()
        //뒤로가기 버튼 만들기
    }


    private fun initLayout(){
        feed_page_detail_activity_starImage_ImageView.setOnClickListener {
            viewModel.starClick()
        }
    }
    private fun initViewModel(){
        viewModel = ViewModelProviders.of(this, ViewModelFactory()).get(FeedPageDetailViewModel::class.java)
        viewModel.bindFeedItem(this.intent.getBooleanExtra("currentClickState", false), this.intent.getIntExtra("currentStarNum", 0),this.intent.getStringExtra("imageSrc"), this.intent.getStringExtra("feedId"))
        binding.viewModel = viewModel
    }
    private fun toolbarInit(){
        setSupportActionBar(feed_page_detail_activity_toolbar_Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = null

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            android.R.id.home->{
                onBackPressed()//기본 디폴트로 나두면 뒤에 있는 엑티비티의 상태가 유지되지 않는다.
                //onBackPressed()그래서 사용
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun finish() {
        val resultIntent = Intent()
        resultIntent.putExtra("currentClickState", viewModel.getCurrentClickState())
        resultIntent.putExtra("currentStarNum", viewModel.getCurrentStarNum())
        resultIntent.putExtra("feedId", viewModel.getFeedId())

        setResult(1, resultIntent)
        super.finish()


    }
}