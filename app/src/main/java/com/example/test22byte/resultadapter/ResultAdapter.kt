package com.example.test22byte.resultadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test22byte.R
import com.example.test22byte.databinding.ItemResultBinding
import com.example.test22byte.question.QuestionResult

class ResultAdapter : ListAdapter<QuestionResult, ResultAdapter.ResultHolder>(DiffCallBack()) {

    inner class ResultHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemResultBinding.bind(itemView)

        fun bind(questionResult: QuestionResult) = with(binding) {
            binding.question.text = questionResult.question
            binding.answer.text = questionResult.isCorrect
        }

    }

    companion object {
        private class DiffCallBack : DiffUtil.ItemCallback<QuestionResult>() {
            override fun areItemsTheSame(
                oldItem: QuestionResult,
                newItem: QuestionResult
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: QuestionResult,
                newItem: QuestionResult
            ): Boolean {
                return oldItem.question == newItem.question
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_result, parent, false)
        return ResultHolder(itemView)
    }

    override fun onBindViewHolder(holder: ResultHolder, position: Int) {
        val questionResult: QuestionResult = getItem(position)
        holder.bind(questionResult)
    }
}