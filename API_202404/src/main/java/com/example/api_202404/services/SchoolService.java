package com.example.api_202404.services;

import com.example.api_202404.entities.School;
import com.example.api_202404.entities.Student;
import com.example.api_202404.repositories.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
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

    public String addSchool(String name, String address) {
        School school = new School(name, address);
        try {
            schoolRepository.saveAndFlush(school);
            return school.getName() + " school added successfully";
        } catch (Exception e) {
            return school.getName() + " school was not added";
        }
    }

    public String addStudentToSchoolbyId(Long schoolId, String firstName, String lastName, Date dateOfBirth, String gender) {
        Student student = new Student(firstName, lastName, dateOfBirth, gender, schoolId);
        try {
            School school = findSchoolById(schoolId);
            school.getStudents().add(student);
            schoolRepository.saveAndFlush(school);
            return student + "\nSuccessfully added to school " + school.getName();
        } catch (Exception e) {
            return "Failed to add " + student;
        }
    }

    public String updateSchoolNameById(Long schoolId, String newName) {

        try {
            School school = findSchoolById(schoolId);
            String odlName = school.getName();
            school.setName(newName);
            schoolRepository.saveAndFlush(school);
            return "School name: "+ odlName + " successfully updated to name " + school.getName();
        } catch (Exception e) {
            return "Failed to update school name";
        }
    }
}
