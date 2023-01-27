package ru.lytvest.kafedra.service

import jakarta.annotation.PostConstruct
import mu.KotlinLogging
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.lytvest.kafedra.dto.UserPrincipal
import ru.lytvest.kafedra.entity.User
import ru.lytvest.kafedra.repository.UserRepository

@Service
class AuthService(
    val userRepository: UserRepository,
    val passwordEncoder: PasswordEncoder

) : UserDetailsService {
    val log = KotlinLogging.logger {}

    @PostConstruct
    fun init() {
        User().apply {
            username = "user"
            password = passwordEncoder.encode("1234")
            confirmed = true
            userRepository.findByUsername(username) ?: run {
                userRepository.save(this)
            }
        }
        User().apply {
            username = "admin"
            password = passwordEncoder.encode("1234")
            roles = "USER,ADMIN"
            confirmed = true
            userRepository.findByUsername(username) ?: run {
                userRepository.save(this)
            }
        }
    }

    override fun loadUserByUsername(username: String): UserDetails {
        log.info { "fing by $username" }
        return userRepository.findByUsername(username)?.let {
            UserPrincipal(it)
        } ?: throw Exception("Пользователь не найден")
    }


    fun registerUser(username: String, password: String, fio: String?): UserDetails? {
        log.info { "register user: $username" }
        return userRepository.findByUsername(username)?.let {
            null
        } ?: run {
            User().let {
                it.username = username
                it.password = passwordEncoder.encode(password)
                it.fio = fio ?: ""
                it.confirmed = true
                UserPrincipal(userRepository.save(it))
            }
        }
    }


}
