package ru.lytvest.kafedra.controllers


import mu.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.lytvest.kafedra.service.AuthService

@Controller
class AuthController(
    val authService: AuthService
) {

    val log = KotlinLogging.logger {}

    @GetMapping("/login")
    fun login(@RequestParam logout: Boolean?, @RequestParam error: Boolean?, model: Model): String {
        model.addAttribute("logout", logout)
        model.addAttribute("error", error)
        return "login"
    }

    @PostMapping("/register")
    fun register(
        @RequestParam username: String,
        @RequestParam password: String,
        @RequestParam fio: String?,
        model: Model
    ): String {
        val user = authService.registerUser(username, password, fio)
        model.addAttribute("registerSuccess", user != null)
        return "register"
    }
}
