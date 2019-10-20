package com.mksoft.mkjw_second_project.ui_view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mksoft.mkjw_second_project.App
import com.mksoft.mkjw_second_project.R
import com.mksoft.mkjw_second_project.databinding.BoardCategoryItemBinding
import com.mksoft.mkjw_second_project.model.Board.BoardCategory
import com.mksoft.mkjw_second_project.viewmodel.BoardCategoryItemViewModel

class BoardCategoryListAdapter: RecyclerView.Adapter<BoardCategoryListAdapter.ViewHolder>(){
    private lateinit var boardCategoryList:List<BoardCategory>



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardCategoryListAdapter.ViewHolder {
        val binding: BoardCategoryItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.board_category_item, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if(::boardCategoryList.isInitialized) boardCategoryList.size else 0
    }

    override fun onBindViewHolder(holder: BoardCategoryListAdapter.ViewHolder, position: Int) {
        holder.bind(boardCategoryList[position])
        holder.itemView.setOnClickListener {
            holder.viewClick()
        }
    }

    fun updateBoardCategory(boardCategoryList: List<BoardCategory>){
        this.boardCategoryList = boardCategoryList
        notifyDataSetChanged()
    }
    class ViewHolder(private val binding: BoardCategoryItemBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel: BoardCategoryItemViewModel
                = BoardCategoryItemViewModel()

        fun bind(boardCategory: BoardCategory){
            viewModel.bind(boardCategory)//뷰모델 바인딩할 값
            binding.viewModel = viewModel
        }
        fun viewClick(){
            viewModel.clickView()
        }
    }
}