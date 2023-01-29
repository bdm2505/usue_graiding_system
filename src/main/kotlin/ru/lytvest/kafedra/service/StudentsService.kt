package ru.lytvest.kafedra.service

import mu.KotlinLogging
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.lytvest.kafedra.entity.Student
import ru.lytvest.kafedra.repository.StudentRepository

@Service
class StudentsService(
    val studentRepository: StudentRepository,
) {
    val log = KotlinLogging.logger {}


    fun findById(id: Long): Student? {
        return studentRepository.findByIdOrNull(id)
    }


}