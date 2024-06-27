package com.gouresh.employeerestapidemo.dao

import com.gouresh.employeerestapidemo.entity.Employee


interface EmployeeDAO {
     fun findById(id : Int) : Employee
     fun findAll(): List<Employee>
     fun save(employee: Employee)
     fun delete(id: Int)
}