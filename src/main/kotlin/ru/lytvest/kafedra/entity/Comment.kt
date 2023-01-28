package ru.lytvest.kafedra.entity

import jakarta.persistence.*
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
    lateinit var examiner: User

    @ManyToOne
    lateinit var exam: Exam

    @ManyToOne
    lateinit var student: Student

    fun toDto(): CommentDto = CommentDto(
        text,
        examiner.fio ?: examiner.username,
        student.fio(),
        exam.name,
        dateTime.toString() // TODO
    )
}