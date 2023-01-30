package ru.lytvest.kafedra.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.lytvest.kafedra.entity.Examiner

interface ExaminerRepository : JpaRepository<Examiner, Long> {

    fun findByLogin(login: String): Examiner?
}
