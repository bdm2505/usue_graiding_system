package ru.lytvest.kafedra.service

import jakarta.annotation.PostConstruct
import jakarta.servlet.http.HttpSession
import org.springframework.stereotype.Service
import ru.lytvest.kafedra.dto.QuestionDto
import ru.lytvest.kafedra.entity.*
import ru.lytvest.kafedra.repository.CommentRepository
import ru.lytvest.kafedra.repository.QuestionRepository

@Service
class QuestionService(
    val commentRepository: CommentRepository,
    val session: HttpSession,
    val questionRepository: QuestionRepository
) {

    @PostConstruct
    fun init() {

        for (i in 1..10) {
            Question().apply {
                text =
                    "Вопрос $i о том как надо жарить рыбу (1 до 10)" + "Вопрос $i о том как надо жарить рыбу (1 до 10)" + "Вопрос $i о том как надо жарить рыбу (1 до 10)"
                questionRepository.save(this)
            }
        }
    }

    fun addComment(text: String, examiner: Examiner, exam: Exam, student: Student): Comment {
        return Comment().let {
            it.text = text
            it.examiner = examiner
            it.student = student
            commentRepository.save(it)
        }
    }

    fun addComment(text: String): Comment {
        val examiner = session.getAttribute("examiner") as Examiner? ?: throw RuntimeException("not found user")
        val exam = session.getAttribute("exam") as Exam? ?: throw RuntimeException("not found exam")
        val student = session.getAttribute("student") as Student? ?: throw RuntimeException("not found exam")

        return addComment(text, examiner, exam, student)
    }

    fun comments(exam: Exam, student: Student? = null, user: User? = null): List<Comment> {
        // TODO
        return listOf()
    }

    fun allQuestions(): List<QuestionDto> {
        return questionRepository.findAllByDeleted(false).map { it.toDto() }
    }
}