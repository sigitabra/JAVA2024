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

        for (Student student : findAllStudentsBySchoolId(schoolId)) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }
}
