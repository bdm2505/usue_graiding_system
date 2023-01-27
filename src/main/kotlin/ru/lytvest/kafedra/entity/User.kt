package ru.lytvest.kafedra.entity

import jakarta.persistence.*
import lombok.Getter
import lombok.Setter

@Entity(name = "users_data")
@Getter
@Setter
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(nullable = false, unique = true)
    var username: String = ""
    var password: String = ""
    var fio = ""
    var roles = "USER"
    var locked = false
    var confirmed = false
}