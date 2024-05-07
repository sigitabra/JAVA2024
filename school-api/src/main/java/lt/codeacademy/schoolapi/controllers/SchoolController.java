package lt.codeacademy.schoolapi.controllers;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.schoolapi.converters.SchoolConverter;
import lt.codeacademy.schoolapi.converters.StudentConverter;
import lt.codeacademy.schoolapi.dto.CreateSchoolRequest;
import lt.codeacademy.schoolapi.dto.CreateSchoolResponse;
import lt.codeacademy.schoolapi.dto.CreateStudentRequest;
import lt.codeacademy.schoolapi.dto.CreateStudentResponse;
import lt.codeacademy.schoolapi.dto.GetSchoolResponse;
import lt.codeacademy.schoolapi.dto.GetStudentResponse;
import lt.codeacademy.schoolapi.entities.School;
import lt.codeacademy.schoolapi.entities.Student;
import lt.codeacademy.schoolapi.services.SchoolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schools")
public class SchoolController {

    private final SchoolService schoolService;

    @GetMapping
    public ResponseEntity<List<GetSchoolResponse>> getAllSchools() {
        List<School> schools = this.schoolService.getAllSchools();

        if (schools.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(SchoolConverter.convertSchoolEntityListToGetResponse(schools));
    }

    @PostMapping
    public ResponseEntity<CreateSchoolResponse> addSchool(@RequestBody CreateSchoolRequest createSchoolRequest) {
        CreateSchoolResponse responseBody =
                SchoolConverter.convertSchoolEntityToCreateResponse(this.schoolService.addSchool(createSchoolRequest));

        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @GetMapping("/{schoolId}")
    public ResponseEntity<GetSchoolResponse> getSchoolById(@PathVariable Long schoolId) {
        School school = this.schoolService.getSchoolById(schoolId);
        if (school == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(SchoolConverter.convertSchoolEntityToGetResponse(school));
    }

    @PatchMapping("/{schoolId}/title")
    public ResponseEntity<GetSchoolResponse> patchSchoolByIdName(@PathVariable Long schoolId,
                                                                 @RequestBody String newTitle) {
        School school = this.schoolService.patchSchoolByIdTitle(schoolId, newTitle);
        if (school == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(SchoolConverter.convertSchoolEntityToGetResponse(school));
    }

    @GetMapping("/{schoolId}/students")
    public ResponseEntity<List<GetStudentResponse>> getStudentsBySchoolId(@PathVariable Long schoolId) {
        List<Student> students = this.schoolService.getAllStudentsBySchoolId(schoolId);
        if (students == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (students.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(StudentConverter.convertStudentEntityListToGetResponse(students));
    }

    @PostMapping("/{schoolId}/students")
    public ResponseEntity<CreateStudentResponse> addStudentToSchoolById(@RequestBody CreateStudentRequest createStudentRequest, @PathVariable Long schoolId) {
        School school = this.schoolService.addStudentToSchoolBySchoolId(schoolId, createStudentRequest);
        if (school == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(SchoolConverter.convertSchoolToCreateStudentResponse(school));
    }

    @GetMapping("/{schoolId}/students/{studentId}")
    public ResponseEntity<GetStudentResponse> getStudentBySchoolIdAndStudentId(@PathVariable Long schoolId,
                                                                               @PathVariable Long studentId) {
        Student student = this.schoolService.getStudentBySchoolIdAndStudentId(schoolId, studentId);

        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(StudentConverter.convertStudentEntityToGetResponse(student));
    }
}
