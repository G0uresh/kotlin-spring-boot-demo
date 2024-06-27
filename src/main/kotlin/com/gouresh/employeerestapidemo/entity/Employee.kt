package com.gouresh.employeerestapidemo.entity

import jakarta.persistence.*

@Entity
@Table(name = "employee")
class Employee(){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Int =  0

    @Column(name = "first_name")
    var firstName:String?=null

    @Column(name = "last_name")
    var lastName:String?=null

    @Column(name = "email")
    var email:String?=null

    constructor(firstName: String?, lastName: String?, email: String?) : this() {
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
    }

    override fun toString(): String {
        return "Employee ( id=$id, firstName=$firstName, lastName=$lastName, email=$email )"
    }


}
