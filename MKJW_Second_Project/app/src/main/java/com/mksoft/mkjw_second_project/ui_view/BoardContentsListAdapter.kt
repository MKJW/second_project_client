package com.mksoft.mkjw_second_project.ui_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mksoft.mkjw_second_project.R
import com.mksoft.mkjw_second_project.databinding.BoardCategoryItemBinding
import com.mksoft.mkjw_second_project.databinding.BoardContentsItemBinding
import com.mksoft.mkjw_second_project.model.Board.BoardCategory
import com.mksoft.mkjw_second_project.model.Board.BoardContents
import com.mksoft.mkjw_second_project.viewmodel.BoardCategoryItemViewModel
import com.mksoft.mkjw_second_project.viewmodel.BoardContentsItemViewModel

class BoardContentsListAdapter: RecyclerView.Adapter<BoardContentsListAdapter.ViewHolder>(){
    private lateinit var boardContentsList:List<BoardContents>



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardContentsListAdapter.ViewHolder {
        val binding: BoardContentsItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.board_contents_item, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if(::boardContentsList.isInitialized) boardContentsList.size else 0
    }

    override fun onBindViewHolder(holder: BoardContentsListAdapter.ViewHolder, position: Int) {
        holder.bind(boardContentsList[position])

    }

    fun updateBoardContents(boardContentsList: List<BoardContents>){
        this.boardContentsList = boardContentsList
        notifyDataSetChanged()
    }
    class ViewHolder(private val binding: BoardContentsItemBinding): RecyclerView.ViewHolder(binding.root){
        private val viewModel: BoardContentsItemViewModel
                = BoardContentsItemViewModel()

        fun bind(boardContents: BoardContents){
            viewModel.bind(boardContents)//뷰모델 바인딩할 값
            binding.viewModel = viewModel
        }

    }
}