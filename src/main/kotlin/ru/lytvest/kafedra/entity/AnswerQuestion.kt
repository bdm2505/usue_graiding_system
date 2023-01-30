package ru.lytvest.kafedra.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import ru.lytvest.kafedra.dto.AnswerDto
import java.time.LocalDateTime

@Entity
class AnswerQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @ManyToOne
    lateinit var examiner: Examiner

    @ManyToOne
    lateinit var student: Student

    @ManyToOne
    lateinit var question: Question

    var count: Int = 0

    var dateTime: LocalDateTime = LocalDateTime.now()

    fun toDto() = AnswerDto(id!!, count, student.fio, examiner.fio, question.text)
}
