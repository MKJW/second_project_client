package com.mksoft.mkjw_second_project.ui_view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mksoft.mkjw_second_project.R
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {
    private val boardPageFragment = BoardPageFragment()
    private val timeTablePageFragment = TimeTablePageFragment()
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

    private fun initBottomLine() {
        main_activity_bottomLine_BottomNavigationView.setOnNavigationItemSelectedListener {
            item ->
            when {
                item.itemId == R.id.bottom_board_button -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_activity_contentsView_FrameLayout, boardPageFragment).commit()
                }
                item.itemId == R.id.bottom_sns_button -> {
                    Toast.makeText(this, "하이2", Toast.LENGTH_LONG).show()

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
}