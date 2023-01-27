package ru.lytvest.kafedra


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@SpringBootApplication
class KafedraApplication() {


//	@Bean
//	fun userDetailsService(): InMemoryUserDetailsManager {
//		val user1: UserDetails = User.withUsername("user")
//			.password(passwordEncoder().encode("1234"))
//			.roles("USER")
//			.build()
//		val user2: UserDetails = User.withUsername("user2")
//			.password(passwordEncoder().encode("1234"))
//			.roles("USER")
//			.build()
//		val admin: UserDetails = User.withUsername("admin")
//			.password(passwordEncoder().encode("1234"))
//			.roles("ADMIN")
//			.build()
//		return object : InMemoryUserDetailsManager(user1, user2, admin) {
//			override fun loadUserByUsername(username: String?): UserDetails {
//				println("load user $username")
//				return super.loadUserByUsername(username)
//			}
//		}
//	}

//	@Bean
//	fun userDetailsService(): AuthService {
//
//
//		return authService;
//	}

	@Bean
	fun passwordEncoder(): PasswordEncoder {
		return BCryptPasswordEncoder()
	}

//	@Bean
//	fun filterChain(http: HttpSecurity): SecurityFilterChain {
//
//
//		http.csrf().disable()
//			.authorizeHttpRequests { auth ->
//				auth.requestMatchers("/login", "/static/**", "/login_auth").permitAll()
////				.requestMatchers("/login_auth").permitAll()
//					.anyRequest().authenticated()
//			}
//			.formLogin().apply {
//				loginPage("/login")
//				loginProcessingUrl("/login_auth")
//				defaultSuccessUrl("/")
//				failureUrl("/login?error=true")
//			}
//			.and()
//			.logout().apply {
//				logoutUrl("/logout")
//				logoutSuccessUrl("/login?logout=true")
//			}
//
//		return http.build()
//	}

}

fun main(args: Array<String>) {
	runApplication<KafedraApplication>(*args)

	println("Server start on address: http://localhost:8080/")
}
