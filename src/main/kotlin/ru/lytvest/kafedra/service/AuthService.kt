package ru.lytvest.kafedra.service

import mu.KotlinLogging
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsPasswordService
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import ru.lytvest.kafedra.dto.UserPrincipal
import ru.lytvest.kafedra.repository.UserRepository

@Service
class AuthService(val userRepository: UserRepository) : UserDetailsService, UserDetailsPasswordService {
    val log = KotlinLogging.logger {}
    override fun loadUserByUsername(username: String): UserDetails {
        log.info { "fing by $username" }
        return userRepository.findByUsername(username)?.let {
            UserPrincipal(it)
        } ?: throw Exception("Пользователь не найден")
    }

    override fun updatePassword(user: UserDetails?, newPassword: String?): UserDetails {
        TODO("Not yet implemented")
    }
}
