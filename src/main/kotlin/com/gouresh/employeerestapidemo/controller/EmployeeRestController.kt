package com.gouresh.employeerestapidemo.controller

import com.gouresh.employeerestapidemo.entity.Employee
import com.gouresh.employeerestapidemo.service.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File

@RestController
@RequestMapping("/api")
class EmployeeRestController {

    @Autowired
    lateinit var employeeService: EmployeeService

    @GetMapping("/employees")
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = ["employee-cache"])
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
    @Transactional
    @CacheEvict(cacheNames = ["employee-cache"] )
    fun deleteEmployee(@PathVariable("employeeId") employeeId: Int){
        employeeService.delete(id = employeeId)
    }

    @PostMapping("employees/uploadFile")
    fun uploadFile(@RequestParam("file") file : MultipartFile)  {
        file.transferTo(File("C:\\Users\\91836\\Documents\\"+file.originalFilename))
    }

}