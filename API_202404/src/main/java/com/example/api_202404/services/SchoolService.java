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

    public List<School> findAllSchools() {
        return schoolRepository.findAll();
    }

    public School findSchoolById(Long id) {

        return schoolRepository.findById(id).orElse(null);
    }

    public List<Student> findAllStudentsBySchoolId(Long id) {
        return findSchoolById(id).getStudents();
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

    public School addSchool(School school) {
        try {
            schoolRepository.saveAndFlush(school);
        } catch (Exception e) {
            System.out.println("ERROR: " + school.getName() + " school was not added");
        }
        return school;
    }

    public Student addStudentToSchoolbyId(Long schoolId, Student student) {
        try {
            School school = findSchoolById(schoolId);
            school.getStudents().add(student);
            schoolRepository.saveAndFlush(school);
        } catch (Exception e) {
            System.out.println("Failed to add " + student);
        }
        return student;
    }

    public School updateSchoolNameById(Long schoolId, String newName) {
        School school = null;
        try {
            school = findSchoolById(schoolId);
            String odlName = school.getName();
            school.setName(newName);
            schoolRepository.saveAndFlush(school);
        } catch (Exception e) {
            System.out.println("Failed to update school name");
        }
        return school;
    }
}
