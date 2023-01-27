package ru.lytvest.kafedra.controllers


import mu.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class AuthController {

    val log = KotlinLogging.logger {}

    @GetMapping("/login")
    fun login(@RequestParam logout: Boolean?, @RequestParam error: Boolean?, model: Model): String {
        model.addAttribute("logout", logout)
        model.addAttribute("error", error)
        return "login"
    }
}
