package ru.lytvest.kafedra.service

import jakarta.servlet.http.HttpSession
import org.springframework.stereotype.Service
import ru.lytvest.kafedra.entity.Comment
import ru.lytvest.kafedra.entity.Exam
import ru.lytvest.kafedra.entity.Student
import ru.lytvest.kafedra.entity.User
import ru.lytvest.kafedra.repository.CommentRepository

@Service
class QuestionService(val commentRepository: CommentRepository, val session: HttpSession) {

    fun addComment(text: String, user: User, exam: Exam, student: Student): Comment {
        return Comment().let {
            it.text = text
            it.examiner = user
            it.student = student
            commentRepository.save(it)
        }
    }

    fun addComment(text: String): Comment {
        val user = session.getAttribute("user") as User? ?: throw RuntimeException("not found user")
        val exam = session.getAttribute("exam") as Exam? ?: throw RuntimeException("not found exam")
        val student = session.getAttribute("student") as Student? ?: throw RuntimeException("not found exam")

        return addComment(text, user, exam, student)
    }

    fun comments(exam: Exam, student: Student? = null, user: User? = null): List<Comment> {
        // TODO
        return listOf()
    }
}