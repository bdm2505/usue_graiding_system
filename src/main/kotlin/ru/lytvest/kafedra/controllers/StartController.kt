package ru.lytvest.kafedra.controllers

import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class StartController {

    @GetMapping("/")
    fun index(
        model: Model,
        authentication: Authentication?
    ): String {
        model.addUserData(authentication)
        return "redirect:/groups"
    }
}