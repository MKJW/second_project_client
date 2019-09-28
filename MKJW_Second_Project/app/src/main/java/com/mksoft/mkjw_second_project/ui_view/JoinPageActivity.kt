package com.mksoft.mkjw_second_project.ui_view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.jakewharton.rxbinding3.widget.textChanges
import com.mksoft.mkjw_second_project.R
import com.mksoft.mkjw_second_project.databinding.JoinPageActivityBinding
import com.mksoft.mkjw_second_project.di.ViewModelFactory
import com.mksoft.mkjw_second_project.viewmodel.JoinViewModel
import kotlinx.android.synthetic.main.join_page_activity.*

class JoinPageActivity : AppCompatActivity(){
    private lateinit var binding: JoinPageActivityBinding
    private lateinit var viewModel: JoinViewModel

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.join_page_activity)
        binding = DataBindingUtil.setContentView(this, R.layout.join_page_activity)
        viewModel = ViewModelProviders.of(this, ViewModelFactory()).get(JoinViewModel::class.java)
        binding.viewModel = viewModel
        changeInputID()
        changeInputPW()
        changeInputChekPW()
        initJoinButton()
    }

    private fun initJoinButton(){
        join_page_activity_join_Button.setOnClickListener {
            viewModel.sendUserForJoin(join_page_activity_inputEmail_EditText.text.toString(),
                join_page_activity_inputPWCheck_EditText.text.toString(),
                join_page_activity_inputName_EditText.text.toString()
                )
        }
    }
    @SuppressLint("CheckResult")
    private fun changeInputID(){
        join_page_activity_inputEmail_EditText
            .textChanges()
            .subscribe{
                val inputID = join_page_activity_inputEmail_EditText.text.toString()
                val IDState = checkProperID(inputID)
                if(inputID.isNotEmpty())
                    viewModel.checkIDState(IDState, inputID)
            }
    }
    //여기페이지에서 검사를 해주고 그에대한 바인딩은 viewModel에서 하도록 하자
    @SuppressLint("CheckResult")
    private fun changeInputPW(){
        join_page_activity_inputPW_EditText
            .textChanges()
            .subscribe{
                val inputPW = join_page_activity_inputPW_EditText.text.toString()
                val PWState = checkProperPW(inputPW)
                if(join_page_activity_inputPWCheck_EditText.text.toString().isEmpty()){
                    if(inputPW.isNotEmpty())
                        viewModel.checkPWState(PWState)
                }else{
                    if(inputPW!=join_page_activity_inputPWCheck_EditText.text.toString()){
                        viewModel.checkSamePW(false)
                    }else{
                        viewModel.checkPWState(PWState)
                    }
                }

            }
    }
    @SuppressLint("CheckResult")
    private fun changeInputChekPW(){
        join_page_activity_inputPWCheck_EditText
            .textChanges()
            .subscribe{
                val inputCheckPW = join_page_activity_inputPWCheck_EditText.text.toString()

                if(inputCheckPW.isNotEmpty()&&(checkProperPW(join_page_activity_inputPW_EditText.text.toString()))){
                    if(inputCheckPW != join_page_activity_inputPW_EditText.text.toString()){
                        viewModel.checkSamePW(false)
                    }else{
                        viewModel.checkSamePW(true)
                    }
                }

            }
    }
    private fun checkProperID(inputID : String) : Boolean{
        var checkEmailState = false
        var checkSpace = false
        for(oneChar in inputID){
            if(oneChar == '@')
                checkEmailState = true
            if(oneChar == ' ')
                checkSpace = true

        }
        return (checkEmailState)&&(!checkSpace)
    }
    private fun checkProperPW(inputPW: String) : Boolean{
        if(inputPW.length<8 || inputPW.length>16){
            return false
        }
        var smallCharIs:Boolean = false
        var largeCharIs: Boolean = false
        var specialCharIs:Boolean = false

        for(oneChar in inputPW){
            if(oneChar in 'a'..'z'){
                smallCharIs = true
            }else if(oneChar in 'A'..'Z'){
                largeCharIs = true
            }else if(oneChar == '!' || oneChar == '@' ||
                oneChar == '#'||oneChar == '$'||oneChar == '%'||oneChar == '^'||
                oneChar == '&'||oneChar == '*'||oneChar == '('||oneChar == ')' ||oneChar == '_'||oneChar == '+'||
                oneChar == '='||oneChar == '-'||oneChar == '|'||oneChar == '`'||oneChar == '<'||oneChar == '>'||
                oneChar == '?'){
                specialCharIs = true
            }
        }
        if(smallCharIs && largeCharIs && specialCharIs){
            return true
        }
        return false
    }

}