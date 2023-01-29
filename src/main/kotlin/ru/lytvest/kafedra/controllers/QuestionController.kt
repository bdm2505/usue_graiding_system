package ru.lytvest.kafedra.controllers

import jakarta.servlet.http.HttpSession
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.lytvest.kafedra.entity.Student
import ru.lytvest.kafedra.service.QuestionService
import ru.lytvest.kafedra.service.StudentsService

@Controller
class QuestionController(
    val studentsService: StudentsService,
    val session: HttpSession,
    val questionService: QuestionService
) {

    @GetMapping("questions")
    fun questions(
        model: Model,
        authentication: Authentication?,
        @RequestParam studentId: Long?
    ): String {
        model.addUserData(authentication)

        val student = studentId?.let { studentsService.findById(studentId) }
            ?: session.getAttribute("student") as Student?
            ?: return "redirect:/groups"

        session.setAttribute("student", student)
        model.addAttribute("student", student.toDto())

        model.addAttribute("questions", questionService.allQuestions())

        return "questions"
    }
}