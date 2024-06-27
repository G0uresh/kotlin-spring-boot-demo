package com.gouresh.employeerestapidemo.repository

import com.gouresh.employeerestapidemo.entity.Employee
import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeRepository : JpaRepository<Employee,Int> {
}