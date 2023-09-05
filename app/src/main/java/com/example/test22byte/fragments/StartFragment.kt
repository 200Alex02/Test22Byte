package com.example.test22byte.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.test22byte.KEY
import com.example.test22byte.R
import com.example.test22byte.basefragment.BaseFragment
import com.example.test22byte.databinding.FragmentStartBinding
import com.example.test22byte.info.result
import com.example.test22byte.question.QuestionResult
import com.example.test22byte.quizviewmodel.QuizViewModel

class StartFragment : BaseFragment<FragmentStartBinding>(
    FragmentStartBinding::inflate
) {

    private lateinit var viewModel: QuizViewModel
    private var correct = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = Bundle()

        result.add(QuestionResult("Вопросы", "Результаты"))

        viewModel = ViewModelProvider(this)[QuizViewModel::class.java]

        updateUI()

        binding.image1.setOnClickListener {
            viewModel.checkAnswer(0)
        }

        binding.image2.setOnClickListener {
            viewModel.checkAnswer(1)
        }

        binding.image3.setOnClickListener {
            viewModel.checkAnswer(2)
        }

        binding.nextButton.setOnClickListener {
            viewModel.moveToNextQuestion()
            if (viewModel.getCurrentQuestionIndex() < viewModel.getQuestionCount()) {
                updateUI()
            } else {
                bundle.putInt(KEY, viewModel.getCorrectAnswersCount())
                showResults(bundle = bundle)
            }
        }
    }

    private fun updateUI() {
        val currentQuestion = viewModel.getQuestion(viewModel.getCurrentQuestionIndex())
        binding.question.text = currentQuestion.question
        binding.image1.setImageResource(currentQuestion.picture[0])
        binding.image2.setImageResource(currentQuestion.picture[1])
        binding.image3.setImageResource(currentQuestion.picture[2])
    }

    /*private fun checkAnswer(selectedAnswerIndex: Int) {
        val currentQuestion = viewModel.getQuestion(viewModel.getCurrentQuestionIndex())
        if (selectedAnswerIndex == currentQuestion.correctAnswer) {
            correct++
            viewModel.incrementCorrectAnswersCount()
        }
        Log.i("1", "${viewModel.getCorrectAnswersCount()}")
        Log.i("2", "$correct")
        result.add(
            QuestionResult(
                "${viewModel.getCurrentQuestionIndex()}",
                (if (selectedAnswerIndex == currentQuestion.correctAnswer) {
                    "Верно"
                } else {
                    "Ошибка"
                }).toString()
            )
        )
        Log.i("4", "${result.size}")
    }*/

    private fun showResults(bundle: Bundle) {
        fragmentManager
            ?.beginTransaction()
            ?.replace(R.id.fragment_container, ResultFragment().apply {
                arguments = bundle
            })
            ?.commit()
    }

}