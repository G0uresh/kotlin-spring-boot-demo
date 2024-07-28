package com.gouresh.employeerestapidemo.repository

import com.gouresh.employeerestapidemo.entity.Employee
import org.aspectj.lang.annotation.After
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringExtension


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRepositoryTest {

    @Autowired
    lateinit var employeeRepository : EmployeeRepository

    lateinit var employee: Employee

    @BeforeEach
    fun setUp() {
        // Initialize test data before each test method test
        employee = Employee(firstName = "Gouresh", lastName = "power", email = "asd@gmail.com")
        employeeRepository.save(employee)
    }

    @AfterEach
    fun tearDown() {
        // Release test data after each test method
        employeeRepository.delete(employee)
    }

    @Test
    fun `test save employee feature`(){
        val empl = employeeRepository.save(employee)
        Assertions.assertThat(empl.id).isGreaterThan(0)
        Assertions.assertThat(empl.firstName).hasToString("Gouresh")
    }

    @Test
    fun `test find By Id function`(){
        val empl = employeeRepository.findById(employee.id).orElseThrow()
        Assertions.assertThat(empl.id).isGreaterThan(0)
        Assertions.assertThat(empl.firstName).hasToString("Gouresh")
    }
}