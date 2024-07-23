package com.gouresh.employeerestapidemo.controller

import com.gouresh.employeerestapidemo.entity.Employee
import com.gouresh.employeerestapidemo.service.EmployeeService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.security.test.context.support.WithUserDetails
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(EmployeeRestController::class)
class EmployeeRestControllerTest {

    @TestConfiguration
    class EmployeeRestControllerConfig {
        @Bean
        @Primary
        fun service() = mockk<EmployeeService>()
    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var employeeService: EmployeeService

    @Test
    @WithUserDetails("gouresh")
    fun testGetEmployee() {
        val employee = createEmployee()
       every { employeeService.findAll() } returns employee
        val result = mockMvc.perform(get("/api/employees"))
            .andExpect(status().isOk)
            .andDo(print())
            .andReturn()
    }

    private fun createEmployee(): List<Employee> {
        val e = Employee(firstName = "asd", lastName = "asd", email = "asd")
        return listOf(e)
    }
}