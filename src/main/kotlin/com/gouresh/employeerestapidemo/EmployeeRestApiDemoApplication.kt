package com.gouresh.employeerestapidemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class EmployeeRestApiDemoApplication

fun main(args: Array<String>) {
    runApplication<EmployeeRestApiDemoApplication>(*args)
}
