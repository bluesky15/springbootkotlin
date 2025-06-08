package com.example.demo

import com.example.demo.model.Student
import com.example.demo.repository.StudentRepository
import org.springframework.stereotype.Service

@Service
class StudentService(private val studentRepository: StudentRepository) {

    fun saveStudent(student: Student): Student {
        return studentRepository.save(student)
    }

    fun getAllStudents(): List<Student> {
        return studentRepository.findAll()
    }

    fun getStudentById(id: Int): Student? {
        return studentRepository.findById(id).orElse(null)
    }

    fun getStudentByName(name: String): List<Student>{
        return studentRepository.findStudentByName(name)
    }

    fun deleteStudent(id: Int) {
        return studentRepository.deleteById(id)
    }
}
