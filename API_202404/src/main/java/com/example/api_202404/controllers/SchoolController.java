package com.example.api_202404.controllers;

import com.example.api_202404.converters.SchoolConverter;
import com.example.api_202404.converters.StudentConverter;
import com.example.api_202404.dto.SchoolDTOInput;
import com.example.api_202404.dto.SchoolDTOOutput;
import com.example.api_202404.dto.StudentDTOInput;
import com.example.api_202404.dto.StudentDTOOutput;
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
    public List<SchoolDTOOutput> getAllSchools() {
        return SchoolConverter.convertEntityListToDTO(schoolService.findAllSchools());
    }

    @PostMapping
    public SchoolDTOOutput addSchool(@RequestBody SchoolDTOInput schoolDTOInput) {
        return SchoolConverter.convertEntityToDTO(schoolService.addSchool(SchoolConverter.convertDTOToEntity(schoolDTOInput)));
    }

    @GetMapping(value = "/{schoolId}")
    public SchoolDTOOutput getSchoolById(@PathVariable Long schoolId) {
        return SchoolConverter.convertEntityToDTO(this.schoolService.findSchoolById(schoolId));
    }

    @PatchMapping(value = "/{schoolId}/name")
    public SchoolDTOOutput updateSchoolNameById(@PathVariable Long schoolId, @RequestBody String newName) {
        return SchoolConverter.convertEntityToDTO(this.schoolService.updateSchoolNameById(schoolId, newName));
    }

    @GetMapping(value = "/{schoolId}/students")
    public List<StudentDTOOutput> getAllStudentsBySchoolId(@PathVariable Long schoolId) {
        return StudentConverter.convertEntityListToDTO(this.schoolService.findAllStudentsBySchoolId(schoolId));
    }

    @PostMapping(value = "/{schoolId}/students")
    public SchoolDTOOutput addStudentToSchoolById(@PathVariable Long schoolId, @RequestBody StudentDTOInput studentDTOInput) {
        return SchoolConverter.convertEntityToDTO(this.schoolService.addStudentToSchoolById(schoolId, StudentConverter.convertDTOtoEntity(studentDTOInput)));
    }

    @GetMapping(value = "/{schoolId}/students/{studentId}")
    public StudentDTOOutput getStudentBySchoolIdAndStudentId(@PathVariable Long schoolId, @PathVariable Long studentId) {
        return StudentConverter.convertEntityToDTO(this.schoolService.findStudentBySchoolIdAndStudentId(schoolId, studentId));
    }

}
