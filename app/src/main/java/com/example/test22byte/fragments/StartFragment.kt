package com.example.test22byte.fragments

import android.os.Bundle
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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = Bundle()

        result.add(QuestionResult("Вопросы", "Результаты"))

        viewModel = ViewModelProvider(this)[QuizViewModel::class.java]

        updateUI()

        binding.checkbox1.setOnClickListener {
            viewModel.checkAnswer(0)
        }

        binding.checkbox2.setOnClickListener {
            viewModel.checkAnswer(1)
        }

        binding.checkbox3.setOnClickListener {
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

            resetCheckBox()
        }
    }

    private fun updateUI() {
        val currentQuestion = viewModel.getQuestion(viewModel.getCurrentQuestionIndex())
        binding.question.text = currentQuestion.question
        binding.image1.setImageResource(currentQuestion.picture[0])
        binding.image2.setImageResource(currentQuestion.picture[1])
        binding.image3.setImageResource(currentQuestion.picture[2])
    }

    private fun showResults(bundle: Bundle) {
        fragmentManager
            ?.beginTransaction()
            ?.replace(R.id.fragment_container, ResultFragment().apply {
                arguments = bundle
            })
            ?.commit()
    }

    private fun resetCheckBox() {
        binding.checkbox1.isChecked = false
        binding.checkbox2.isChecked = false
        binding.checkbox3.isChecked = false
    }
}