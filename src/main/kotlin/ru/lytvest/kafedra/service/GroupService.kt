package ru.lytvest.kafedra.service

import org.springframework.stereotype.Service
import ru.lytvest.kafedra.dto.GroupDto
import ru.lytvest.kafedra.dto.StudentDto
import ru.lytvest.kafedra.entity.Exam
import ru.lytvest.kafedra.entity.Group
import ru.lytvest.kafedra.repository.GroupRepository

@Service
class GroupService(
    val groupRepository: GroupRepository
) {

    fun groups(exam: Exam? = null): List<GroupDto> {
        if (exam == null)
            return groupRepository.findAll().map { it.toDto() }

        return exam.groups.map { it.toDto() }
    }

    fun studentsByGroup(name: String): List<StudentDto> {
        return groupRepository.findByName(name)?.let { group ->
            group.students.map { it.toDto() }
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