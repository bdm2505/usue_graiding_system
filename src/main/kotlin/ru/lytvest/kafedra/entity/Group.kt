package ru.lytvest.kafedra.entity

import jakarta.persistence.*
import ru.lytvest.kafedra.dto.GroupDto
import java.time.LocalDate

@Entity
@Table(
    name = "group_data",
    uniqueConstraints = [UniqueConstraint(columnNames = ["name"])]
)
class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null


    var name: String = ""

    val date: LocalDate = LocalDate.now()

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var students: MutableList<Student> = mutableListOf()

    fun toDto() = GroupDto(id!!, name)

}