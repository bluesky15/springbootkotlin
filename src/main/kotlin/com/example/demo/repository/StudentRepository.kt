package com.example.demo.repository

import com.example.demo.model.Student
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository : JpaRepository<Student, Int> {

    fun findStudentByName(name:String): List<Student>
}