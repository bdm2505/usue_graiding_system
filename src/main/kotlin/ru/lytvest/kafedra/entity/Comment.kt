package ru.lytvest.kafedra.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import ru.lytvest.kafedra.dto.CommentDto
import java.time.LocalDateTime

@Entity
class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(length = 1000)
    var text: String = ""

    var dateTime: LocalDateTime = LocalDateTime.now()

    @ManyToOne
    lateinit var examiner: Examiner

    @ManyToOne
    var exam: Exam? = null

    @ManyToOne
    lateinit var student: Student


    fun toDto(): CommentDto = CommentDto(
        text,
        examiner.fio,
        student.fio,
        exam?.name ?: "Нет имени экзамена",
        dateTime.toString() // TODO
    )
}
