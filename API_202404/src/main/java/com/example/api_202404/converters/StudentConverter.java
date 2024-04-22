package com.example.api_202404.converters;

import com.example.api_202404.dto.SchoolDTOOutgoing;
import com.example.api_202404.dto.StudentDTOIncoming;
import com.example.api_202404.dto.StudentDTOOutgoing;
import com.example.api_202404.entities.School;
import com.example.api_202404.entities.Student;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class StudentConverter {


    public static StudentDTOOutgoing convertEntityToDTO(Student student) {
        StudentDTOOutgoing studentDTOOutgoing = null;
        if (student != null) {
            studentDTOOutgoing = new StudentDTOOutgoing();
            studentDTOOutgoing.setId(student.getId());
            studentDTOOutgoing.setFirstName(student.getLastName());
            studentDTOOutgoing.setLastName(student.getLastName());
            studentDTOOutgoing.setDateOfBirth(student.getDateOfBirth());
            studentDTOOutgoing.setGender(student.getGender());
            studentDTOOutgoing.setSchoolId(student.getSchoolId());
        }
        return studentDTOOutgoing;
    }

    public static List<StudentDTOOutgoing> convertEntityListToDTO(List<Student> students) {
        List<StudentDTOOutgoing> studentDTOList = null;
        if (students != null && !students.isEmpty()) {
            studentDTOList = new ArrayList<>();
            for (Student student : students) {
                studentDTOList.add(convertEntityToDTO(student));
            }
        }
        return studentDTOList;
    }

    public static Student convertDTOtoEntity(StudentDTOIncoming studentDTO) {
        Student student = null;
        if (studentDTO != null) {
            student = new Student();
            student.setFirstName(studentDTO.getLastName());
            student.setLastName(studentDTO.getLastName());
            student.setDateOfBirth(studentDTO.getDateOfBirth());
            student.setEmail(studentDTO.getEmail());
            student.setGender(studentDTO.getGender());
            student.setPhoneNumber(studentDTO.getPhoneNumber());
            student.setAddress(studentDTO.getAddress());
            student.setSchoolId(studentDTO.getSchoolId());
        }
        return student;
    }



}
