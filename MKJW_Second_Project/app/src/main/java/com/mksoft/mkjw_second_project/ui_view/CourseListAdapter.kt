package com.mksoft.mkjw_second_project.ui_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mksoft.mkjw_second_project.model.Course.Course
import com.mksoft.mkjw_second_project.R
import com.mksoft.mkjw_second_project.viewmodel.RegisterCourseViewModel
import com.mksoft.mkjw_second_project.databinding.RegisterCoursesPageItemBinding

class CourseListAdapter: RecyclerView.Adapter<CourseListAdapter.ViewHolder>(){
    private lateinit var courseList:List<Course>



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseListAdapter.ViewHolder {
        val binding: RegisterCoursesPageItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.register_courses_page_item, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if(::courseList.isInitialized) courseList.size else 0
    }

    override fun onBindViewHolder(holder: CourseListAdapter.ViewHolder, position: Int) {
        holder.bind(courseList[position])
    }

    fun updateCourseList(courseList: List<Course>){
        this.courseList = courseList
        notifyDataSetChanged()
    }
    class ViewHolder(private val binding: RegisterCoursesPageItemBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel: RegisterCourseViewModel
                = RegisterCourseViewModel()
        //각 리스트 아이템 별로 다른 뷰모델을 갖고있게 한다는건 하나의 아이템으로 연결을 하겠다는 것과 같은 이야기가 된다.
        //그래서 이 부분은 DI적용 없이 그냥 만들자.

        fun bind(course:Course){
            viewModel.bind(course)//뷰모델 바인딩할 값
            binding.viewModel = viewModel
        }//예전 프로젝트에서는 어뎁터 바인딩을 어뎁터에서 진행을 했는데
        //어뎁터를 뷰로 분리한다면 바인딩은 뷰모델에서 하도록 분리하는 것이 명료해 지는거 같다.
        //그래서 위 코드처럼 어뎁터에서 바인딩이 가능함에도 불구하고 바인딩을 뷰모델에서 하도록 분리한 듯 하다.
    }
}