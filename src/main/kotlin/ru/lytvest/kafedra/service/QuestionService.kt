package ru.lytvest.kafedra.service

import jakarta.annotation.PostConstruct
import jakarta.servlet.http.HttpSession
import org.springframework.stereotype.Service
import ru.lytvest.kafedra.dto.AnswerDto
import ru.lytvest.kafedra.dto.QuestionDto
import ru.lytvest.kafedra.entity.*
import ru.lytvest.kafedra.repository.AnswerQuestionRepository
import ru.lytvest.kafedra.repository.CommentRepository
import ru.lytvest.kafedra.repository.QuestionRepository

@Service
class QuestionService(
    val commentRepository: CommentRepository,
    val session: HttpSession,
    val questionRepository: QuestionRepository,
    val answerQuestionRepository: AnswerQuestionRepository
) {

    @PostConstruct
    fun init() {

        for (i in 1..10) {
            Question().apply {
                text = if (i % 2 == 0)
                    "Вопрос $i о том как надо жарить рыбу (1 до 10)"
                else
                    "Вопрос $i о том как не надо прыгать из парашута в грозу (1 до 10)"
                questionRepository.save(this)
            }
        }
    }

    fun addComment(text: String, examiner: Examiner, student: Student): Comment {
        return Comment().let {
            it.text = text
            it.examiner = examiner
            it.student = student
            commentRepository.save(it)
        }
    }

    fun addComment(text: String): Comment {
        val examiner = session.getAttribute("examiner") as Examiner? ?: throw RuntimeException("not found user")
        val student = session.getAttribute("student") as Student? ?: throw RuntimeException("not found exam")

        return addComment(text, examiner, student)
    }

    fun comments(exam: Exam, student: Student? = null, user: User? = null): List<Comment> {
        // TODO
        return listOf()
    }

    fun saveAnswer(
        examiner: Examiner,
        student: Student,
        answers: Map<Question, String>,
        comment: String
    ): List<AnswerDto> {
        val res = answers.map { (quest, num) ->
            AnswerQuestion().let {
                it.examiner = examiner
                it.student = student
                it.question = quest
                it.count = num.toInt()
                answerQuestionRepository.save(it).toDto()
            }
        }
        addComment(comment, examiner, student)
        return res
    }

    fun allQuestionsDto(): List<QuestionDto> {
        return questionRepository.findAllByDeleted(false).map { it.toDto() }
    }

    fun allQuestions(): List<Question> {
        return questionRepository.findAllByDeleted(false)
    }
}
