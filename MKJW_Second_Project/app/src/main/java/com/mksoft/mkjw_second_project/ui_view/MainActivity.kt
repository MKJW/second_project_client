package com.mksoft.mkjw_second_project.ui_view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mksoft.mkjw_second_project.App
import com.mksoft.mkjw_second_project.R
import com.mksoft.mkjw_second_project.utils.BackPressCloseHandler
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {
    private val backPressCloseHandler: BackPressCloseHandler = BackPressCloseHandler(this)

    private val boardPageFragment = BoardPageFragment()
    private val timeTablePageFragment = TimeTablePageFragment()
    private val feedPageFragment = FeedPageFragment()

    private val testFragment = TestFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        initMainView()
        initBottomLine()
    }

    @SuppressLint("ResourceType")
    private fun initMainView(){
        supportFragmentManager.beginTransaction().replace(R.id.main_activity_contentsView_FrameLayout, boardPageFragment).commit()

    }
    override fun onBackPressed() {
        backPressCloseHandler.onBackPressed()
    }
    private fun initBottomLine() {
        main_activity_bottomLine_BottomNavigationView.setOnNavigationItemSelectedListener {
            item ->
            when {
                item.itemId == R.id.bottom_board_button -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_activity_contentsView_FrameLayout, boardPageFragment).commit()
                }
                item.itemId == R.id.bottom_sns_button -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_activity_contentsView_FrameLayout, feedPageFragment).commit()

                }
                item.itemId == R.id.bottom_time_table_button -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_activity_contentsView_FrameLayout, timeTablePageFragment).commit()

                }
                item.itemId == R.id.bottom_option_button ->{

                }

            }

            return@setOnNavigationItemSelectedListener true
        }
    }

    override fun onActivityReenter(resultCode: Int, data: Intent?) {
        super.onActivityReenter(resultCode, data)
        if(resultCode == 1){
            Toast.makeText(this, "test",Toast.LENGTH_LONG).show()
        }
    }
}