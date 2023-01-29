package ru.lytvest.kafedra.service

import jakarta.annotation.PostConstruct
import mu.KotlinLogging
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.lytvest.kafedra.Utils
import ru.lytvest.kafedra.dto.UserPrincipal
import ru.lytvest.kafedra.entity.Examiner
import ru.lytvest.kafedra.entity.User
import ru.lytvest.kafedra.repository.ExaminerRepository
import ru.lytvest.kafedra.repository.UserRepository
import java.util.*

@Service
class AuthService(
    val userRepository: UserRepository,
    val passwordEncoder: PasswordEncoder,
    val examinerRepository: ExaminerRepository

) : UserDetailsService {
    val log = KotlinLogging.logger {}

    @PostConstruct
    fun init() {

        User().apply {
            username = "user"
            password = passwordEncoder.encode("1234")
            confirmed = true
            roles = "USER"
            userRepository.findByUsername(username) ?: run {
                userRepository.save(this)
            }
        }
        User().apply {
            username = "admin"
            password = passwordEncoder.encode("1234")
            roles = "ADMIN"
            confirmed = true
            userRepository.findByUsername(username) ?: run {
                userRepository.save(this)
            }
        }
        User().apply {
            username = "tomskih"
            password = passwordEncoder.encode("1234")
            roles = "USER"
            fio = "Тайвин Ланистер"
            confirmed = true
            userRepository.findByUsername(username) ?: run {
                userRepository.save(this)
            }
        }
        User().apply {
            username = "nastya59"
            password = passwordEncoder.encode("1234")
            roles = "ADMIN"
            fio = "Иванов Иван Иванович"
            confirmed = true
            userRepository.findByUsername(username) ?: run {
                userRepository.save(this)
            }
        }
    }

    override fun loadUserByUsername(username: String): UserDetails {
        log.info { "find by $username" }
        return userRepository.findByUsername(username)?.let {
            UserPrincipal().apply { user = it }
        } ?: throw Exception("Пользователь не найден")
    }


//    fun registerUser(username: String, password: String, fio: String?): UserDetails? {
//        log.info { "register user: $username" }
//        val user1 = userRepository.findByUsername(username)
//        if (user1 != null)
//            return null
//
//        return User().let {
//            it.username = username
//            it.password = passwordEncoder.encode(password)
//            it.fio = fio ?: ""
//            it.confirmed = true
//            UserPrincipal().apply { user = userRepository.save(it) }
//        }
//    }

    fun registerExaminer(login: String, fio: String, password: String? = null): Examiner {
        User().let {
            it.username = login
            it.password = passwordEncoder.encode(password ?: UUID.randomUUID().toString())
            it.fio = fio
            it.confirmed = password != null
            UserPrincipal().apply { user = userRepository.save(it) }
        }

        return Examiner().apply {
            this.login = login
            this.fio = fio
            examinerRepository.save(this)
        }
    }

}
