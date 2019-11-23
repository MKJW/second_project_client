package com.mksoft.mkjw_second_project.ui_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mksoft.mkjw_second_project.R
import com.mksoft.mkjw_second_project.databinding.FeedItemFirstBinding
import com.mksoft.mkjw_second_project.databinding.FeedItemSecondBinding
import com.mksoft.mkjw_second_project.databinding.FeedItemThirdBinding
import com.mksoft.mkjw_second_project.databinding.RegisterCoursesPageItemBinding
import com.mksoft.mkjw_second_project.model.Course.Course
import com.mksoft.mkjw_second_project.model.SNS.Feed
import com.mksoft.mkjw_second_project.viewmodel.FeedItemViewModel
import com.mksoft.mkjw_second_project.viewmodel.RegisterCourseViewModel

class FeedListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private lateinit var feedList:List<Feed>


    override fun getItemViewType(position: Int): Int {
        return position % 3
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            0 -> {
                val layoutId = R.layout.feed_item_first
                val binding: FeedItemFirstBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutId, parent, false)

                return FeedFirstViewHolder(binding)
            }
            1 -> {
                val layoutId = R.layout.feed_item_second
                val binding: FeedItemSecondBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutId, parent, false)

                return FeedSecondViewHolder(binding)
            }
            else -> {
                val layoutId = R.layout.feed_item_third
                val binding: FeedItemThirdBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutId, parent, false)

                return FeedThirdViewHolder(binding)
            }
        }

    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int){
        when(position%3){
            0->{
                (holder as FeedFirstViewHolder).bind(feedList[position])
            }
            1->{
                (holder as FeedSecondViewHolder).bind(feedList[position])

            }
            else ->{
                (holder as FeedThirdViewHolder).bind(feedList[position])

            }
        }
    }
    override fun getItemCount(): Int {
        return feedList.size
    }
    fun updateFeedList(feedList: List<Feed>){
        this.feedList = feedList
        notifyDataSetChanged()
    }

    class FeedFirstViewHolder(private val binding: FeedItemFirstBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel: FeedItemViewModel
                = FeedItemViewModel()

        fun bind(feedItem:Feed){
            viewModel.bind(feedItem)//뷰모델 바인딩할 값
            binding.viewModel = viewModel
        }
    }
    class FeedSecondViewHolder(private val binding: FeedItemSecondBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel: FeedItemViewModel
                = FeedItemViewModel()

        fun bind(feedItem:Feed){
            viewModel.bind(feedItem)//뷰모델 바인딩할 값
            binding.viewModel = viewModel
        }
    }
    class FeedThirdViewHolder(private val binding: FeedItemThirdBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel: FeedItemViewModel
                = FeedItemViewModel()
        fun bind(feedItem:Feed){
            viewModel.bind(feedItem)//뷰모델 바인딩할 값
            binding.viewModel = viewModel
        }
    }

}