//package com.gouresh.employeerestapidemo.dao
//
//import com.gouresh.employeerestapidemo.entity.Employee
//import jakarta.persistence.EntityManager
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.stereotype.Repository
//
//@Repository
//class EmployeeDAOImpl @Autowired constructor(val entityManager: EntityManager) : EmployeeDAO {
//    override fun findById(id: Int): Employee {
//       val employee = entityManager.find(Employee::class.java,id)
//        return employee
//    }
//
//    override fun findAll(): List<Employee> {
//      val query = entityManager.createQuery("FROM Employee",Employee::class.java)
//       return query.resultList
//    }
//
//    override fun save(employee: Employee) {
//        entityManager.merge(employee)
//    }
//
//
//    override fun delete(id: Int) {
//        val employee = entityManager.find(Employee::class.java,id)
//        entityManager.remove(employee)
//    }
//}
