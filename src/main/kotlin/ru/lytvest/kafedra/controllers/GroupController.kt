package ru.lytvest.kafedra.controllers

import mu.KotlinLogging
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.lytvest.kafedra.service.GroupService

@Controller
class GroupController(
    val groupService: GroupService
) {

    val log = KotlinLogging.logger {}

    @GetMapping("/groups")
    fun groups(
        model: Model,
        authentication: Authentication?,
        @RequestParam group: String?
    ): String {
        model.addUserData(authentication)
        model.addAttribute("groups", groupService.groups())
        if (group != null) {
            val students = groupService.studentsByGroup(group)
            if (students.isNotEmpty()) {
                model.addAttribute("current_group", group)
                model.addAttribute("students", students)
            }
        }
        log.info { model }
        return "index"
    }
}