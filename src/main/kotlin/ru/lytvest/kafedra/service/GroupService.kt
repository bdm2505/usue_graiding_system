package ru.lytvest.kafedra.service

import jakarta.annotation.PostConstruct
import mu.KotlinLogging
import org.springframework.stereotype.Service
import ru.lytvest.kafedra.dto.GroupDto
import ru.lytvest.kafedra.dto.StudentDto
import ru.lytvest.kafedra.entity.Exam
import ru.lytvest.kafedra.entity.Group
import ru.lytvest.kafedra.entity.Student
import ru.lytvest.kafedra.repository.GroupRepository

@Service
class GroupService(
    val groupRepository: GroupRepository
) {
    val log = KotlinLogging.logger {}

    @PostConstruct
    fun init() {
        for (i in 13..21)
            for (j in 1..4) {
                try {
                    addGroup("ИВТ-$i-$j").apply {
                        students.add(Student().apply { firstName = "Дмитрий" })
                        students.add(Student().apply { firstName = "Дмитрий Михалыч" })
                        groupRepository.save(this)
                    }
                } catch (e: Exception) {
                    log.error(e.message)
                }
            }
    }

    fun groups(exam: Exam? = null): List<GroupDto> {
        if (exam == null)
            return groupRepository.findAll().map { it.toDto() }

        return exam.groups.map { it.toDto() }
    }

    fun groupsByName(name: String): List<GroupDto> {
        return groupRepository.findAllByNameContainingIgnoreCase(name).map { it.toDto() };
    }

    fun studentsByGroup(name: String, studentName: String): List<StudentDto> {

        return groupRepository.findByName(name)?.let { group ->
            group.students.filter { it.fio().contains(studentName, ignoreCase = true) }.map { it.toDto() }
        } ?: listOf()
    }

    fun addGroup(name: String): Group {
        return Group().let {
            it.name = name
            groupRepository.save(it)
        }
    }

    fun removeGroup(name: String): Boolean {
        return groupRepository.findByName(name)?.let {
            groupRepository.delete(it)
            true
        } ?: false
    }


}