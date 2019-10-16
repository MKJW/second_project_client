package com.mksoft.mkjw_second_project.ui_view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.mksoft.mkjw_second_project.App
import com.mksoft.mkjw_second_project.R
import com.mksoft.mkjw_second_project.di.ViewModelFactory
import com.mksoft.mkjw_second_project.viewmodel.JoinViewModel
import com.mksoft.mkjw_second_project.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.login_page_activity.*

class LoginPageActivity : AppCompatActivity(){
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)//키보드가 화면을 가릴 때
        setContentView(R.layout.login_page_activity)
        //로그인 페이지에서 FCM 토큰 갱신
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token
                Log.d("testToken", token)
            })



        viewModel = ViewModelProviders.of(this, ViewModelFactory()).get(LoginViewModel::class.java)

        initLoginButton()
        initJoinButton()
    }
    private fun initLoginButton(){
        login_page_activity_signIn_Button.setOnClickListener {
            viewModel.login(login_page_activity_inputID_EditText.text.toString(), login_page_activity_inputPW_EditText.text.toString())
        }

    }
    private fun initJoinButton(){
        login_page_activity_signUp_Button.setOnClickListener {
            val intent = Intent(this, JoinPageActivity::class.java)
            startActivity(intent)
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }
}