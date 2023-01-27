package ru.lytvest.kafedra.dto

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import ru.lytvest.kafedra.entity.User

class UserPrincipal(
    val user: User
) : UserDetails {
    val roles = user.roles.split(",").map { Role(it) }.toMutableList()
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = roles

    override fun getPassword(): String = user.password

    override fun getUsername(): String = user.username

    override fun isAccountNonExpired(): Boolean = user.confirmed

    override fun isAccountNonLocked(): Boolean = !user.locked

    override fun isCredentialsNonExpired(): Boolean = !user.locked

    override fun isEnabled(): Boolean = user.confirmed && !user.locked
}
