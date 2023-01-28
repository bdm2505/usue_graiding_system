package ru.lytvest.kafedra.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.lytvest.kafedra.entity.Group

interface GroupRepository : JpaRepository<Group, Long> {


}
