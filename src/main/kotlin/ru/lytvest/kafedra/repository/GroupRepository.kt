package ru.lytvest.kafedra.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import ru.lytvest.kafedra.entity.Group

interface GroupRepository : JpaRepository<Group, Long> {
    fun findByName(name: String): Group?

    fun findAllByNameContainingIgnoreCase(name: String): List<Group>
}
