package ru.lytvest.kafedra

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.hierarchicalroles.RoleHierarchy
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.channel.ChannelProcessingFilter
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler
import org.springframework.security.web.authentication.RememberMeServices


@Configuration
@EnableWebSecurity
class AuthConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity, rememberMeServices: RememberMeServices?): SecurityFilterChain? {
        http.addFilterBefore(EncodingFilter(), ChannelProcessingFilter::class.java)

            .csrf().disable()
            .authorizeHttpRequests { auth ->
                auth.requestMatchers("/login**", "/static/**").permitAll()
//				.requestMatchers("/login_auth").permitAll()
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
//            .and()
//            .rememberMe { remember: RememberMeConfigurer<HttpSecurity?> ->
//                remember
//                    .rememberMeServices(rememberMeServices)
//            }
        return http.build()
    }

//    @Bean
//    fun rememberMeServices(userDetailsService: UserDetailsService?): RememberMeServices? {
//        val encodingAlgorithm = RememberMeTokenAlgorithm.SHA256
//        val rememberMe = TokenBasedRememberMeServices("myKey", userDetailsService, encodingAlgorithm)
//        rememberMe.setMatchingAlgorithm(RememberMeTokenAlgorithm.SHA256)
//        return rememberMe
//    }

    @Bean
    fun roleHierarchy(): RoleHierarchy {
        val roleHierarchy = RoleHierarchyImpl()
        val hierarchy = "ADMIN > USER"
        roleHierarchy.setHierarchy(hierarchy)
        return roleHierarchy
    }

    @Bean
    fun webSecurityExpressionHandler(): DefaultWebSecurityExpressionHandler? {
        val expressionHandler = DefaultWebSecurityExpressionHandler()
        expressionHandler.setRoleHierarchy(roleHierarchy())
        return expressionHandler
    }
}