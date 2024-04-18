package com.example.api_202404.controllers;

import com.example.api_202404.entities.School;
import com.example.api_202404.entities.Student;
import com.example.api_202404.services.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(originPatterns = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/schools")
public class SchoolController {

    private final SchoolService schoolService;

    @GetMapping
    public List<School> getAllSchools() {
        return schoolService.findAllSchools();
    }

    @GetMapping(value = "/{id}")
    public School getSchoolById(@PathVariable Long id) {
        return this.schoolService.findSchoolById(id);
    }

    @GetMapping(value = "/students/{schoolId}")
    public List<Student> getAllStudentsBySchoolId(@PathVariable Long schoolId) {
        return this.schoolService.findAllStudentsBySchoolId(schoolId);
    }

    @GetMapping(value = "/{schoolId}/students/{studentId}")
    public Student getStudentBySchoolIdAndStudentId(@PathVariable Long schoolId, @PathVariable Long studentId) {
        return this.schoolService.findStudentBySchoolIdAndStudentId(schoolId, studentId);
    }
}
