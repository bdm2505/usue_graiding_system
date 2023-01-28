package ru.lytvest.kafedra.dto

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import ru.lytvest.kafedra.entity.User

class UserPrincipal(

) : UserDetails {
    lateinit var user: User
    val roles: MutableList<Role> by lazy { user.roles.split(",").map { Role(it) }.toMutableList() }
    val fio: String by lazy { user.fio.toString() }

    fun isAdmin() = roles.contains(Role("ADMIN"))
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = roles

    override fun getPassword(): String = user.password

    override fun getUsername(): String = user.username

    override fun isAccountNonExpired(): Boolean = user.confirmed

    override fun isAccountNonLocked(): Boolean = !user.locked

    override fun isCredentialsNonExpired(): Boolean = !user.locked

    override fun isEnabled(): Boolean = user.confirmed && !user.locked
}
