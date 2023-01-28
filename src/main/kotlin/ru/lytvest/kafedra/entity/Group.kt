package ru.lytvest.kafedra.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(
    uniqueConstraints = [UniqueConstraint(columnNames = ["name"])]
)
class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null


    var name: String = ""

    val date: LocalDate = LocalDate.now()

    @OneToMany(fetch = FetchType.LAZY)
    var students: MutableList<Student> = mutableListOf()

}