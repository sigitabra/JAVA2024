package com.example.api_202404.controllers;

import com.example.api_202404.converters.SchoolConverter;
import com.example.api_202404.converters.StudentConverter;
import com.example.api_202404.dto.SchoolDTOIncoming;
import com.example.api_202404.dto.SchoolDTOOutgoing;
import com.example.api_202404.dto.StudentDTOIncoming;
import com.example.api_202404.dto.StudentDTOOutgoing;
import com.example.api_202404.entities.School;
import com.example.api_202404.entities.Student;
import com.example.api_202404.services.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@CrossOrigin(originPatterns = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/schools")
public class SchoolController {

    private final SchoolService schoolService;


    @GetMapping
    public List<SchoolDTOOutgoing> getAllSchools() {
        return SchoolConverter.convertEntityListToDTO(schoolService.findAllSchools());
    }

    @GetMapping(value = "/{id}")
    public SchoolDTOOutgoing getSchoolById(@PathVariable Long id) {
        return SchoolConverter.convertEntityToDTO(this.schoolService.findSchoolById(id));
    }

    @GetMapping(value = "/students/{schoolId}")
    public List<StudentDTOOutgoing> getAllStudentsBySchoolId(@PathVariable Long schoolId) {
        return StudentConverter.convertEntityListToDTO(this.schoolService.findAllStudentsBySchoolId(schoolId));
    }

    @GetMapping(value = "/{schoolId}/students/{studentId}")
    public StudentDTOOutgoing getStudentBySchoolIdAndStudentId(@PathVariable Long schoolId, @PathVariable Long studentId) {
        return StudentConverter.convertEntityToDTO(this.schoolService.findStudentBySchoolIdAndStudentId(schoolId, studentId));
    }

    @PostMapping
    public String addSchool(@RequestBody SchoolDTOIncoming schoolDTOIncoming) {
        return "New school added:\n" + SchoolConverter.convertEntityToDTO(schoolService.addSchool(SchoolConverter.convertDTOToEntity(schoolDTOIncoming)));
    }

    @PostMapping(value = "/schoolId/{schoolId}")
    public String addStudentToSchoolbyId(@PathVariable Long schoolId, @RequestBody StudentDTOIncoming studentDTOIncoming) {
        return "New student added to school "+ SchoolConverter.convertEntityToDTO(schoolService.findSchoolById(schoolId)).getName()+"\n" +StudentConverter.convertEntityToDTO(this.schoolService.addStudentToSchoolbyId(schoolId, StudentConverter.convertDTOtoEntity(studentDTOIncoming)));
    }

    @PatchMapping(value = "/schoolId/{schoolId}")
    public String updateSchoolNameById(@PathVariable Long schoolId, @RequestBody String newName) {
        return SchoolConverter.convertEntityToDTO(schoolService.findSchoolById(schoolId)).getName()+ " school name updated to: "+ SchoolConverter.convertEntityToDTO(this.schoolService.updateSchoolNameById(schoolId, newName)).getName();
    }
}
