package com.gouresh.employeerestapidemo.service

import com.gouresh.employeerestapidemo.entity.Employee
import com.gouresh.employeerestapidemo.repository.EmployeeRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.*

@SpringBootTest
class EmployeeServiceImplTest {

    @Autowired
    private lateinit var employeeService: EmployeeService

    @MockBean
    private lateinit var employeeRepository: EmployeeRepository

    @Test
    fun `should return employee object base on id`() {
        val employeeId = 1;
        val expectedEmployee = Employee(firstName = "Gouresh", lastName = "power", email = "asd@gmail.com")
        // mock
        `when`(employeeRepository.findById(employeeId).get()).thenReturn(expectedEmployee)
        // test
        val employee = employeeService.findById(employeeId)

        Assertions.assertThat(employee?.firstName).isEqualTo(expectedEmployee.firstName)
    }
}