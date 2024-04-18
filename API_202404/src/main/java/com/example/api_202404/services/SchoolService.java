package com.example.api_202404.services;

import com.example.api_202404.entities.School;
import com.example.api_202404.entities.Student;
import com.example.api_202404.repositories.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;

    public List<School> findAll() {
        return schoolRepository.findAll();
    }

    public School findById(Long id) {
        return schoolRepository.findById(id).orElse(null);
    }

    public List<Student> findAllStudentsBySchoolId(Long id) {
        return findById(id).getStudents();
    }

    public Student findStudentBySchoolIdAndStudentId(Long schoolId, Long studentId) {
        List<Student> students = findAllStudentsBySchoolId(schoolId);
        Student tempStudent = new Student(studentId);
        try {
            return students.get(students.indexOf(tempStudent));
        } catch (IndexOutOfBoundsException e) {
            System.out.print("ERROR: There is no student with id " + studentId);
        }
        return null;

    }
}
