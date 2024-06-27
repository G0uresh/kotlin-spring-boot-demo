package com.gouresh.employeerestapidemo.service

import com.gouresh.employeerestapidemo.entity.Employee

interface EmployeeService {

    fun findById(id : Int) : Employee?
    fun findAll(): List<Employee>
    fun save(employee: Employee)
    fun delete(id: Int)
}