package com.example.demo

import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository : JpaRepository<Student, Int>{

    fun findStudentByName(name:String): List<Student>
}
