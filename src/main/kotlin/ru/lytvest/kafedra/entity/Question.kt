package ru.lytvest.kafedra.entity

import jakarta.persistence.*
import ru.lytvest.kafedra.dto.QuestionDto

@Entity
class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(length = 1000)
    var text: String = ""

    var deleted: Boolean = false

    fun toDto() = QuestionDto(id!!, text)
}