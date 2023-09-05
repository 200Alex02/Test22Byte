package com.example.test22byte.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.example.test22byte.KEY
import com.example.test22byte.basefragment.BaseFragment
import com.example.test22byte.databinding.FragmentResultBinding
import com.example.test22byte.info.result
import com.example.test22byte.resultadapter.ResultAdapter

class ResultFragment : BaseFragment<FragmentResultBinding>(
    FragmentResultBinding::inflate
) {

    private lateinit var adapter: ResultAdapter

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ResultAdapter()

        binding.recyclerView.adapter = adapter

        adapter.submitList(result)

        binding.countCorrectAnswer.text =
            "Правильных ответов: ${arguments?.getInt(KEY, 20).toString()}"
    }
}