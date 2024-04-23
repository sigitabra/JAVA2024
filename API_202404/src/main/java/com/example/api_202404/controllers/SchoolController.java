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

    @GetMapping(value = "/{schoolId}")
    public SchoolDTOOutput getSchoolById(@PathVariable Long schoolId) {
        return SchoolConverter.convertEntityToDTO(this.schoolService.findSchoolById(schoolId));
    }

    @GetMapping(value = "/{schoolId}/students")
    public List<StudentDTOOutput> getAllStudentsBySchoolId(@PathVariable Long schoolId) {
        return StudentConverter.convertEntityListToDTO(this.schoolService.findAllStudentsBySchoolId(schoolId));
    }

    @GetMapping(value = "/{schoolId}/students/{studentId}")
    public StudentDTOOutput getStudentBySchoolIdAndStudentId(@PathVariable Long schoolId, @PathVariable Long studentId) {
        return StudentConverter.convertEntityToDTO(this.schoolService.findStudentBySchoolIdAndStudentId(schoolId, studentId));
    }

    @PostMapping
    public String addSchool(@RequestBody SchoolDTOInput schoolDTOInput) {
        return "New school added:\n" + SchoolConverter.convertEntityToDTO(schoolService.addSchool(SchoolConverter.convertDTOToEntity(schoolDTOInput)));
    }

    @PostMapping(value = "/{schoolId}")
    public String addStudentToSchoolbyId(@PathVariable Long schoolId, @RequestBody StudentDTOInput studentDTOInput) {
        return "New student added to school " + SchoolConverter.convertEntityToDTO(schoolService.findSchoolById(schoolId)).getName() + "\n" + StudentConverter.convertEntityToDTO(this.schoolService.addStudentToSchoolById(schoolId, StudentConverter.convertDTOtoEntity(studentDTOInput)));
    }

    @PatchMapping(value = "/{schoolId}/name")
    public String updateSchoolNameById(@PathVariable Long schoolId, @RequestBody String newName) {
        return SchoolConverter.convertEntityToDTO(schoolService.findSchoolById(schoolId)).getName() + " school name updated to: " + SchoolConverter.convertEntityToDTO(this.schoolService.updateSchoolNameById(schoolId, newName)).getName();
    }
}
