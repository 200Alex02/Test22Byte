package com.example.test22byte.quizviewmodel

import androidx.lifecycle.ViewModel
import com.example.test22byte.info.result
import com.example.test22byte.question.Question
import com.example.test22byte.list
import com.example.test22byte.question.QuestionResult

class QuizViewModel : ViewModel() {
    private val questionAndAnswer = list

    private var currentQuestionIndex = 0
    private var countCorrectAnswers = 0
    private var count = 0

    fun getQuestion(index: Int): Question {
        return questionAndAnswer[index]
    }

    fun getQuestionCount(): Int {
        return questionAndAnswer.size
    }

    fun incrementCorrectAnswersCount() {
        countCorrectAnswers++
    }

    fun checkAnswer(selectedAnswerIndex: Int) {
        count++
        if (selectedAnswerIndex == getQuestion(currentQuestionIndex).correctAnswer) {
            incrementCorrectAnswersCount()
            result.add(
                QuestionResult(
                    "Вопрос $count",
                    "Верно"
                )
            )
        } else {
            result.add(
                QuestionResult(
                    "Вопрос $count",
                    "Ошибка"
                )
            )
        }
    }

    fun getCurrentQuestionIndex(): Int {
        return currentQuestionIndex
    }

    fun moveToNextQuestion() {
        currentQuestionIndex++
    }

    fun getCorrectAnswersCount(): Int {
        return countCorrectAnswers
    }

}