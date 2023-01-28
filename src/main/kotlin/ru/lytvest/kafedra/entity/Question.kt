package ru.lytvest.kafedra.entity

import jakarta.persistence.*

@Entity
class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(length = 1000)
    var text: String = ""
}