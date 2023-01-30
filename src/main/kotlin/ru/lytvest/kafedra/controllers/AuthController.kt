package ru.lytvest.kafedra.controllers


import mu.KotlinLogging
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.lytvest.kafedra.dto.UserPrincipal
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


//    @Secured("ADMIN")
//    @PostMapping("/register_form")
//    fun registerForm(
//        @RequestParam username: String,
//        @RequestParam password: String,
//        @RequestParam fio: String?,
//        model: Model,
//        authentication: Authentication?
//    ): String {
//        val user = authService.registerUser(username, password, fio)
//        model.addAttribute("registerSuccess", user != null)
//        model.addUserData(authentication)
//        return "register_form"
//    }
//
//    @Secured("ADMIN")
//    @GetMapping("/register")
//    fun register(
//        model: Model,
//        authentication: Authentication?
//    ): String {
//        model.addUserData(authentication)
//        return "register"
//    }
}

fun Model.addUserData(authentication: Authentication?) {

    authentication?.let { auth ->
        addAttribute("username", auth.name)
        val user = auth.principal
        if (user is UserPrincipal) {
            addAttribute("username", user.username)
            addAttribute("fio", user.fio)
            addAttribute("admin", user.isAdmin())
        }
    }
}
