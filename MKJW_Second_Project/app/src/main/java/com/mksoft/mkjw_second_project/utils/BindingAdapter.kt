package com.mksoft.mkjw_second_project.utils

import android.annotation.TargetApi
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.mksoft.mkjw_second_project.R

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value?: View.VISIBLE})
        //MutableLiveData을 쓰는 방법은 옵저브(주인 엑티비티, 변경시 변경된 값 바인딩해주는 옵저버)
        //그래서 현제 뷰의 부모 엑티비티(주인)가 필요
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text  = value?:""})//""의 의미는 뭘까?---널이면
    }
}
@BindingAdapter("mutableBackground")
fun setMutableBackground(view: View, background: MutableLiveData<Int>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && background != null) {
        background.observe(parentActivity, Observer { value -> view.setBackgroundResource(value?: R.drawable.null_background)})//
    }
}
@BindingAdapter("mutableButtonLockState")
fun setMutableButtonLockState(view: Button, state: MutableLiveData<Boolean>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && state != null) {
        state.observe(parentActivity, Observer { value -> if(!value)view.setOnClickListener(null)})//버튼이 눌러지지 않는다.
    }
}