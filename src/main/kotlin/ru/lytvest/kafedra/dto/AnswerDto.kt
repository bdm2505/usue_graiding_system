package ru.lytvest.kafedra.dto


data class AnswerDto(
    val id: Long,
    val num: Int,
    val studentName: String,
    val examinerName: String,
    val questionText: String
)
