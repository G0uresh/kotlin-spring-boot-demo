package com.gouresh.employeerestapidemo.controller

import com.gouresh.employeerestapidemo.entity.Employee
import com.gouresh.employeerestapidemo.service.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class EmployeeRestController {

    @Autowired
    lateinit var employeeService: EmployeeService

    @GetMapping("/employees")
    fun getAllEmployees() : List<Employee>{
       return employeeService.findAll()
    }

    @GetMapping("/employees/{employeeId}")
    fun getEmployeeById(@PathVariable("employeeId") employeeId : Int) : Employee{
        val employee = employeeService.findById(id = employeeId) ?: throw RuntimeException("Employee not found")
        return employee
    }

    @PostMapping("/employees")
    fun createEmployee(@RequestBody employee: Employee){
        employee.id = 0
        employeeService.save(employee)
    }

    @PutMapping("/employees")
    fun updateEmployee(@RequestBody employee: Employee){
        employeeService.save(employee)
    }

    @DeleteMapping("employees/{employeeId}")
    fun deleteEmployee(@PathVariable("employeeId") employeeId: Int){
        employeeService.delete(id = employeeId)
    }
}