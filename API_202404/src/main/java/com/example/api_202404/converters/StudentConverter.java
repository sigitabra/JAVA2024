package com.example.api_202404.converters;

import com.example.api_202404.dto.StudentDTOInput;
import com.example.api_202404.dto.StudentDTOOutput;
import com.example.api_202404.entities.Student;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentConverter {

    public static StudentDTOOutput convertEntityToDTO(Student student) {
        StudentDTOOutput studentDTOOutput = null;
        if (student != null) {
            studentDTOOutput = new StudentDTOOutput();
            studentDTOOutput.setId(student.getId());
            studentDTOOutput.setFirstName(student.getFirstName());
            studentDTOOutput.setLastName(student.getLastName());
            studentDTOOutput.setDateOfBirth(student.getDateOfBirth());
            studentDTOOutput.setGender(student.getGender());
            studentDTOOutput.setSchoolId(student.getSchoolId());
        }
        return studentDTOOutput;
    }

    public static List<StudentDTOOutput> convertEntityListToDTO(List<Student> students) {
        List<StudentDTOOutput> studentDTOList = null;
        if (students != null && !students.isEmpty()) {
            studentDTOList = new ArrayList<>();
            for (Student student : students) {
                studentDTOList.add(convertEntityToDTO(student));
            }
        }
        return studentDTOList;
    }

    public static Student convertDTOtoEntity(StudentDTOInput studentDTO) {
        Student student = null;
        if (studentDTO != null) {
            student = new Student();
            student.setFirstName(studentDTO.getFirstName());
            student.setLastName(studentDTO.getLastName());
            student.setDateOfBirth(studentDTO.getDateOfBirth());
            student.setEmail(studentDTO.getEmail());
            student.setGender(studentDTO.getGender());
            student.setPhoneNumber(studentDTO.getPhoneNumber());
            student.setAddress(studentDTO.getAddress());
            student.setSchoolId(studentDTO.getSchoolId());
            student.setCreatedAt(Date.valueOf(LocalDate.now()));
            student.setUpdatedAt(Date.valueOf(LocalDate.now()));
        }
        return student;
    }


}
