package com.gouresh.employeerestapidemo.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.provisioning.JdbcUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import javax.sql.DataSource

@Configuration
class SpringSecurityConfigDemo() {

    @Bean
    fun userManagerDetails(dataSource: DataSource): JdbcUserDetailsManager {
        // take default table users and authorities table created in DB
        return JdbcUserDetailsManager(dataSource).apply {
            //To override default table and give custom table for user and roles
            usersByUsernameQuery = "select username, password, enabled FROM custom_users where username=?"
            setAuthoritiesByUsernameQuery("select username, authority FROM custom_authorities where username=?")
        }
    }

    @Bean
    fun filter(http: HttpSecurity): SecurityFilterChain {
        return with(http) {
            authorizeHttpRequests {
                it.requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                it.requestMatchers(HttpMethod.GET, "/api/employees/*").hasRole("EMPLOYEE")
                it.requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                it.requestMatchers(HttpMethod.PUT, "/api/employees/*").hasRole("MANAGER")
                it.requestMatchers(HttpMethod.DELETE, "/api/employees/*").hasRole("ADMIN")
            }
            csrf { it.disable() }
            httpBasic(Customizer.withDefaults())
            build()
        }
    }

//    @Bean
//    fun userDetailsManager(): InMemoryUserDetailsManager {
//        val gouresh = User.builder()
//            .username("gouresh")
//            .password("{noop}test123")
//            .roles("EMPLOYEE", "MANAGER", "ADMIN")
//            .build()
//
//        val susan = User.builder()
//            .username("susan")
//            .password("{noop}test1234")
//            .roles("EMPLOYEE", "MANAGER")
//            .build()
//
//        val bhushan = User.builder()
//            .username("bhushan")
//            .password("{noop}test1")
//            .roles("EMPLOYEE")
//            .build()
//
//        return InMemoryUserDetailsManager(bhushan, susan, gouresh)
//    }
}