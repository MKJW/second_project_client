package com.mksoft.mkjw_second_project.ui_view

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.ScrollView
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mksoft.mkjw_second_project.App
import com.mksoft.mkjw_second_project.R
import com.mksoft.mkjw_second_project.databinding.BoardCategoryItemBinding
import com.mksoft.mkjw_second_project.model.Board.BoardCategory
import com.mksoft.mkjw_second_project.model.Board.BoardCategoryContents
import com.mksoft.mkjw_second_project.viewmodel.BoardCategoryItemViewModel

class BoardCategoryListAdapter: RecyclerView.Adapter<BoardCategoryListAdapter.ViewHolder>(){
    private lateinit var boardCategoryContentsList:List<BoardCategoryContents>



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardCategoryListAdapter.ViewHolder {
        val binding: BoardCategoryItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.board_category_item, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if(::boardCategoryContentsList.isInitialized) boardCategoryContentsList.size else 0
    }

    override fun onBindViewHolder(holder: BoardCategoryListAdapter.ViewHolder, position: Int) {
        holder.bind(boardCategoryContentsList[position])
        holder.itemView.setOnClickListener {
            holder.viewClick()
        }
        holder.initRecyclerView()

    }

    fun updateBoardCategory(boardCategoryContentsList: List<BoardCategoryContents>){
        this.boardCategoryContentsList = boardCategoryContentsList
        notifyDataSetChanged()
    }
    class ViewHolder(private val binding: BoardCategoryItemBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel: BoardCategoryItemViewModel
                = BoardCategoryItemViewModel()

        fun bind(boardCategoryContents: BoardCategoryContents){
            viewModel.bind(boardCategoryContents)//뷰모델 바인딩할 값
            binding.viewModel = viewModel

        }
        fun viewClick(){
            viewModel.clickView(binding.boardCategoryItemContentsListRecyclerView, binding.boardCategoryItemLayout)

        }
        fun initRecyclerView(){
            val linearLayoutManager = LinearLayoutManager(App.applicationContext())
            binding.boardCategoryItemContentsListRecyclerView.layoutManager = linearLayoutManager
            binding.boardCategoryItemContentsListRecyclerView.setHasFixedSize(true)
        }
    }
}