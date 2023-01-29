package ru.lytvest.kafedra.entity

import jakarta.persistence.*
import ru.lytvest.kafedra.dto.StudentDto
import java.time.LocalDate

@Entity
class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    var fio: String = ""

    var dateRegister: LocalDate = LocalDate.now()

    @ManyToOne
    lateinit var group: Group

    fun toDto() = StudentDto(id!!, fio)

}