package ru.lytvest.kafedra


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.channel.ChannelProcessingFilter


@SpringBootApplication
class KafedraApplication() {


	@Bean
	fun userDetailsService(): InMemoryUserDetailsManager {
		val user1: UserDetails = User.withUsername("user")
			.password(passwordEncoder().encode("1234"))
			.roles("USER")
			.build()
		val user2: UserDetails = User.withUsername("user2")
			.password(passwordEncoder().encode("1234"))
			.roles("USER")
			.build()
		val admin: UserDetails = User.withUsername("admin")
			.password(passwordEncoder().encode("1234"))
			.roles("ADMIN")
			.build()
		return InMemoryUserDetailsManager(user1, user2, admin)
	}

//	@Bean
//	fun userDetailsService(): AuthService {
//
//		ru.lytvest.kafedra.entity.User().apply {
//			username = "user"
//			password = "1234"
//			userRepository.findByUsername(username) ?: run {
//				userRepository.save(this)
//			}
//		}
//		ru.lytvest.kafedra.entity.User().apply {
//			username = "admin"
//			password = "1234"
//			roles = "USER,ADMIN"
//			userRepository.findByUsername(username) ?: run {
//				userRepository.save(this)
//			}
//		}
//		return authService;
//	}

	@Bean
	fun passwordEncoder(): PasswordEncoder {
		return BCryptPasswordEncoder()
	}

	@Bean
	fun filterChain(http: HttpSecurity): SecurityFilterChain {
		http.addFilterBefore(EncodingFilter(), ChannelProcessingFilter::class.java)

		http.csrf().disable()
			.authorizeHttpRequests { auth ->
				auth.requestMatchers("/login*").permitAll()
					.anyRequest().authenticated()
			}
			.formLogin().apply {
				loginPage("/login")
				loginProcessingUrl("/login_auth")
				defaultSuccessUrl("/")
				failureUrl("/login?error=true")
			}
			.and()
			.logout().apply {
				logoutUrl("/logout")
				logoutSuccessUrl("/login?logout=true")
			}

		return http.build()
	}

}

fun main(args: Array<String>) {
	runApplication<KafedraApplication>(*args)

	println("Server start on address: http://localhost:8080/")
}
