package com.gouresh.employeerestapidemo.controller

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.gouresh.employeerestapidemo.entity.Employee
import com.gouresh.employeerestapidemo.service.EmployeeService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


//@ExtendWith(SpringExtension::class)
@WebMvcTest
class EmployeeRestControllerTest {

    @MockBean
    lateinit var employeeService: EmployeeService

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    fun `test getAllEmployees should return employees`() {

        `when`(employeeService.findAll()).thenReturn(getEmployeeList())

        val mvcResult = mockMvc.perform(get("/api/employees")).andExpect {
            status().isOk
        }.andReturn()
        val employeeList = objectMapper.readValue(mvcResult.response.contentAsString, object : TypeReference<List<Employee?>?>() {})
        Assertions.assertThat(employeeList?.size).isEqualTo(2)
    }

}

fun getEmployeeList(): MutableList<Employee> {
    val employee1 = Employee(firstName = "employee1", lastName = "power", email = "employee1@gmail.com")
    val employee2 = Employee(firstName = "Employee2", lastName = "power", email = "Employee2@gmail.com")
    return mutableListOf(employee1, employee2)
}