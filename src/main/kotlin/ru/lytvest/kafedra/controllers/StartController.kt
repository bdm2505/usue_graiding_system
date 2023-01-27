package ru.lytvest.kafedra.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class StartController {

    @GetMapping("/")
    fun index(): String {
        println(">>>>")
        return "index"
    }
}