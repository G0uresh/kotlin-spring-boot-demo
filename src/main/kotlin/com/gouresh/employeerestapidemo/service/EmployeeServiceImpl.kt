package com.gouresh.employeerestapidemo.service

import com.gouresh.employeerestapidemo.entity.Employee
import com.gouresh.employeerestapidemo.repository.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EmployeeServiceImpl @Autowired constructor(val employeeRepository: EmployeeRepository) : EmployeeService {

    override fun findById(id: Int): Employee? {
        val employee = employeeRepository.findById(id)
        if(employee.isPresent.not()){
            throw RuntimeException("Employee not found")
        }
        return employee.get()

    }

    override fun findAll(): List<Employee> {
        return employeeRepository.findAll()
    }

    @Transactional
    override fun save(employee: Employee) {
        employeeRepository.save(employee)
    }

    @Transactional
    override fun delete(id: Int) {
        employeeRepository.deleteById(id)
    }


}