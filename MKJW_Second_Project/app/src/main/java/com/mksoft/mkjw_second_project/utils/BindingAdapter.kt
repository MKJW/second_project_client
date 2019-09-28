package com.mksoft.mkjw_second_project.utils

import android.annotation.TargetApi
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.widget.Button
import android.widget.TableLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.mksoft.mkjw_second_project.App
import com.mksoft.mkjw_second_project.R
import java.util.*

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
@BindingAdapter("mutableWidth")
fun setMutableWidth(view: View, width: MutableLiveData<Int>?){
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && width!=null){
        width.observe(parentActivity, Observer{ value -> view.layoutParams.width =
            ((value?:0)* Resources.getSystem().displayMetrics.density).toInt()//dp를 인수로 받고 px로 변환
        })
    }
}//간격 설정
@BindingAdapter("mutableHeight")
fun setMutableHeight(view: View, height: MutableLiveData<Int>?){
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && height!=null){
        height.observe(parentActivity, Observer{ value -> view.layoutParams.height =
            ((value?:0)* Resources.getSystem().displayMetrics.density).toInt()//dp를 인수로 받고 px로 변환
        })
    }
}//높이 설정
@RequiresApi(Build.VERSION_CODES.M)
@BindingAdapter("mutableTextColor")
fun setMutableTextColor(view: TextView, color: MutableLiveData<Int>?){
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && color!=null){
        color.observe(parentActivity, Observer{ value -> view.setTextColor(App.applicationContext().getColor(value?:R.color.nullColor))
        })
    }
}//높이 설정
@BindingAdapter("mutableButtonLockState")
fun setMutableButtonLockState(view: Button, state: MutableLiveData<Boolean>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && state != null) {
        state.observe(parentActivity, Observer { value -> if(!value)view.setOnClickListener(null)})//버튼이 눌러지지 않는다.
    }
}

