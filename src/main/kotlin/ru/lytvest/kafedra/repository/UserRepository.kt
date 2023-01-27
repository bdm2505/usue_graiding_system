package ru.lytvest.kafedra.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.lytvest.kafedra.entity.User

interface UserRepository : JpaRepository<User, Long> {

    fun findByUsername(username: String): User?

}
