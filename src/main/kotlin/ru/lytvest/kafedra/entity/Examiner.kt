package ru.lytvest.kafedra.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDate

@Entity
class Examiner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    var login: String = ""
    var firstName: String = ""
    var lastName: String = ""
    var patronymic: String = ""

    var dateRegister: LocalDate = LocalDate.now()


    fun fio(): String =
        "$lastName $firstName $patronymic"
}