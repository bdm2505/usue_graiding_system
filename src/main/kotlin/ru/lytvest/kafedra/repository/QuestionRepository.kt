package ru.lytvest.kafedra.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.lytvest.kafedra.entity.Question

interface QuestionRepository : JpaRepository<Question, Long> {


}
