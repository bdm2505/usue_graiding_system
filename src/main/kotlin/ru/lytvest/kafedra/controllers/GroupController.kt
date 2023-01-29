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
        @RequestParam group: String?,
        @RequestParam targetName: String?
    ): String {
        model.addUserData(authentication)

        if (group != null) {

            val students = groupService.studentsByGroup(group, targetName ?: "")
            if (students.isNotEmpty()) {
                model.addAttribute("current_group", group)
                model.addAttribute("students", students)
                model.addAttribute("resultsCount", students.size)
            }
        }
        else {

            val groups = groupService.groupsByName(targetName ?: "")

            model.addAttribute("groups", groups)
            model.addAttribute("resultsCount", groups.size)
        }

        model.addAttribute("targetName", targetName)
        model.addAttribute("showBlock", targetName != null || group != null)

        log.info { model }

        return "index"
    }
}