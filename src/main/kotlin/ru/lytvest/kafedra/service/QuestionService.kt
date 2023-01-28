package ru.lytvest.kafedra.service

import jakarta.servlet.http.HttpSession
import org.springframework.stereotype.Service
import ru.lytvest.kafedra.entity.*
import ru.lytvest.kafedra.repository.CommentRepository

@Service
class QuestionService(val commentRepository: CommentRepository, val session: HttpSession) {

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
}