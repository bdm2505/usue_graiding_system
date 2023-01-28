package ru.lytvest.kafedra.dto

data class CommentDto(
    val text: String,
    val author: String,
    val student: String,
    val exam: String,
    val dateTime: String
)