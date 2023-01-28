package ru.lytvest.kafedra.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    // название предмета
    var name: String = ""

    var date: LocalDate = LocalDate.now()

    @OneToMany(fetch = FetchType.LAZY)
    var examiners: MutableList<User> = mutableListOf()

    @OneToMany(fetch = FetchType.LAZY)
    var groups: MutableList<Group> = mutableListOf()
}