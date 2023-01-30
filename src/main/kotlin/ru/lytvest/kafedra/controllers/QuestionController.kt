package ru.lytvest.kafedra.controllers

import jakarta.servlet.http.HttpSession
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.lytvest.kafedra.entity.Student
import ru.lytvest.kafedra.service.ExaminerService
import ru.lytvest.kafedra.service.QuestionService
import ru.lytvest.kafedra.service.StudentsService

@Controller
class QuestionController(
    val studentsService: StudentsService,
    val session: HttpSession,
    val questionService: QuestionService,
    val examinerService: ExaminerService
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

        model.addAttribute("questions", questionService.allQuestionsDto())

        return "questions"
    }

    @PostMapping("save_answer")
    fun saveAnswer(
        model: Model,
        authentication: Authentication?,
        @RequestParam params: Map<String, String>
    ): String {
        model.addUserData(authentication)
        val studentId = params["studentId"]?.toLong()

        val examiner = examinerService.findByLogin(model.getAttribute("username") as String)
            ?: throw RuntimeException("не найден экзаминатор")

        val student = studentId?.let { studentsService.findById(studentId) }
            ?: session.getAttribute("student") as Student?
            ?: throw RuntimeException("не указан id студента")

        val questions = questionService.allQuestions()

        val answers = questions.map {
            it to (params["quest-${it.id}"] ?: throw RuntimeException("не указан отвен на quest-${it.id} студента"))
        }.toMap()

        val comment = params["comment"] ?: ""

        val answersResult = questionService.saveAnswer(examiner, student, answers, comment)

        model.addAttribute("answers", answersResult)
        model.addAttribute("comment", comment)

        return "save_answer"
    }
}
