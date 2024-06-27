package com.gouresh.employeerestapidemo.repository

import com.gouresh.employeerestapidemo.entity.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path = "members")
interface EmployeeRepository : JpaRepository<Employee,Int> {
}