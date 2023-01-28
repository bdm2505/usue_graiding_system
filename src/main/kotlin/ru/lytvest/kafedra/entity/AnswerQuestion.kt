package ru.lytvest.kafedra.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class AnswerQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @ManyToOne
    lateinit var examiner: User

    @ManyToOne
    lateinit var student: Student

    @ManyToOne
    lateinit var question: Question

    val count: Int = 0

    var dateTime: LocalDateTime = LocalDateTime.now()
}