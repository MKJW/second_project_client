package com.mksoft.mkjw_second_project.ui_view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mksoft.mkjw_second_project.App
import com.mksoft.mkjw_second_project.R
import com.mksoft.mkjw_second_project.databinding.FeedItemFirstBinding
import com.mksoft.mkjw_second_project.databinding.FeedItemSecondBinding
import com.mksoft.mkjw_second_project.databinding.FeedItemThirdBinding
import com.mksoft.mkjw_second_project.databinding.RegisterCoursesPageItemBinding
import com.mksoft.mkjw_second_project.model.Course.Course
import com.mksoft.mkjw_second_project.model.SNS.Feed
import com.mksoft.mkjw_second_project.viewmodel.FeedItemViewModel
import com.mksoft.mkjw_second_project.viewmodel.RegisterCourseViewModel

class FeedListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var feedList: List<Feed>

    override fun getItemViewType(position: Int): Int {
        return position % 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            0 -> {
                val layoutId = R.layout.feed_item_first
                val binding: FeedItemFirstBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    layoutId,
                    parent,
                    false
                )

                return FeedFirstViewHolder(binding)
            }
            1 -> {
                val layoutId = R.layout.feed_item_second
                val binding: FeedItemSecondBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    layoutId,
                    parent,
                    false
                )

                return FeedSecondViewHolder(binding)
            }
            else -> {
                val layoutId = R.layout.feed_item_third
                val binding: FeedItemThirdBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    layoutId,
                    parent,
                    false
                )

                return FeedThirdViewHolder(binding)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (position % 3) {
            0 -> {
                (holder as FeedFirstViewHolder).bind(feedList[position])
            }
            1 -> {
                (holder as FeedSecondViewHolder).bind(feedList[position])

            }
            else -> {
                (holder as FeedThirdViewHolder).bind(feedList[position])

            }
        }
    }

    override fun getItemCount(): Int {
        return feedList.size
    }

    fun updateFeedList(feedList: List<Feed>) {
        this.feedList = feedList
        notifyDataSetChanged()
    }

    class FeedFirstViewHolder(private val binding: FeedItemFirstBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val viewModel: FeedItemViewModel = FeedItemViewModel()

        fun bind(feedItem: Feed) {
            viewModel.bind(feedItem)//뷰모델 바인딩할 값
            binding.viewModel = viewModel
            binding.feedItemStarImageView.setOnClickListener {
                binding.feedItemStarImageView.setImageResource(viewModel.starClick())

            }
            binding.feedItemFeedImageNetworkImageView.setOnClickListener {
                val nextIntent = Intent(App.applicationContext(),FeedPageDetailActivity::class.java)
                nextIntent.putExtra("currentClickState", viewModel.currentClickState)
                nextIntent.putExtra("currentStarNum", viewModel.currentStarNum)
                nextIntent.putExtra("imageSrc", viewModel.getImageSrcString())
                App.applicationContext().startActivity(nextIntent)
            }
        }
    }

    class FeedSecondViewHolder(private val binding: FeedItemSecondBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val viewModel: FeedItemViewModel = FeedItemViewModel()

        fun bind(feedItem: Feed) {
            viewModel.bind(feedItem)//뷰모델 바인딩할 값

            binding.viewModel = viewModel
            binding.feedItemStarImageView.setOnClickListener {

                binding.feedItemStarImageView.setImageResource(viewModel.starClick())
            }
            binding.feedItemFeedImageNetworkImageView.setOnClickListener {
                val nextIntent = Intent(App.applicationContext(),FeedPageDetailActivity::class.java)
                nextIntent.putExtra("currentClickState", viewModel.currentClickState)
                nextIntent.putExtra("currentStarNum", viewModel.currentStarNum)
                nextIntent.putExtra("imageSrc", viewModel.getImageSrcString())
                App.applicationContext().startActivity(nextIntent)
            }
        }
    }

    //뷰모델에서 별의 상태를 갖고있고 이를 위 클릭 리스너에서 뷰에게 눌린걸 전달하고
    //이때의 상태를 변경해주면서 like num 또한 같이 올려주자...
    //그리고 내부 디비에 자신이 좋아요를 한 리스트를 저장하자
    //피드 아이디 관리가 필요해 보인다.
    //누를때마다 좋아요 상태를 전송하나? 흐음...
    class FeedThirdViewHolder(private val binding: FeedItemThirdBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val viewModel: FeedItemViewModel = FeedItemViewModel()
        fun bind(feedItem: Feed) {
            viewModel.bind(feedItem)//뷰모델 바인딩할 값

            binding.viewModel = viewModel
            binding.feedItemStarImageView.setOnClickListener {

                binding.feedItemStarImageView.setImageResource(viewModel.starClick())
            }
            binding.feedItemFeedImageNetworkImageView.setOnClickListener {
                val nextIntent = Intent(App.applicationContext(),FeedPageDetailActivity::class.java)
                nextIntent.putExtra("currentClickState", viewModel.currentClickState)
                nextIntent.putExtra("currentStarNum", viewModel.currentStarNum)
                nextIntent.putExtra("imageSrc", viewModel.getImageSrcString())
                App.applicationContext().startActivity(nextIntent)
            }
        }
    }

}