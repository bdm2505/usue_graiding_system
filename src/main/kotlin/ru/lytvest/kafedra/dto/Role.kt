package ru.lytvest.kafedra.dto

import org.springframework.security.core.GrantedAuthority

class Role(val role: String) : GrantedAuthority {
    override fun getAuthority(): String = role
}
