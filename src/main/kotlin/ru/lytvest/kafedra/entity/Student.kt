package ru.lytvest.kafedra.entity

import jakarta.persistence.*
import ru.lytvest.kafedra.dto.StudentDto
import java.time.LocalDate

@Entity
class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    var firstName: String = ""
    var lastName: String = ""
    var patronymic: String = ""

    var dateRegister: LocalDate = LocalDate.now()

    @ManyToOne
    lateinit var group: Group

    fun fio(): String =
        "$lastName $firstName $patronymic"

    fun toDto() = StudentDto(id!!, fio())

}