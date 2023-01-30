package ru.lytvest.kafedra.service

import mu.KotlinLogging
import org.springframework.stereotype.Service
import ru.lytvest.kafedra.entity.Examiner
import ru.lytvest.kafedra.repository.ExaminerRepository

@Service
class ExaminerService(
    val examinerRepository: ExaminerRepository
) {
    val log = KotlinLogging.logger {}


    fun findByLogin(login: String): Examiner? {
        return examinerRepository.findByLogin(login)
    }


}
