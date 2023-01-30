package ru.lytvest.kafedra.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.time.LocalDate

@Entity
@Table(
    uniqueConstraints = [UniqueConstraint(columnNames = ["login"])]
)
class Examiner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    var login: String = ""
    var fio: String = ""

    var dateRegister: LocalDate = LocalDate.now()
}
