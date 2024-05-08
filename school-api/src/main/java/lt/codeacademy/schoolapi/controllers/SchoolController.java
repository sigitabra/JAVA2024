package lt.codeacademy.schoolapi.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import lt.codeacademy.schoolapi.validations.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
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
    public ResponseEntity<?> addSchool(@Valid @RequestBody CreateSchoolRequest createSchoolRequest, BindingResult bindingResult) {
        log.info("Request addSchool received");
        log.debug("Adding new school: {}", createSchoolRequest);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ValidationError (bindingResult.getFieldErrors()));
        }
        CreateSchoolResponse responseBody =
                SchoolConverter.convertSchoolEntityToCreateResponse(this.schoolService.addSchool(createSchoolRequest));
        log.info("New school added");
        log.debug("New school: {}", responseBody);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @GetMapping("/{schoolId}")
    public ResponseEntity<GetSchoolResponse> getSchoolById(@PathVariable Long schoolId) {
        log.info("Request getSchoolById received");
        log.debug("Get school by id: {}", schoolId);
        School school = this.schoolService.getSchoolById(schoolId);
        if (school == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        log.info("School posted");
        log.debug("School posted: {}", school);
        return ResponseEntity.ok(SchoolConverter.convertSchoolEntityToGetResponse(school));
    }

    @PatchMapping("/{schoolId}/title")
    public ResponseEntity<?> patchSchoolByIdName(@PathVariable Long schoolId,
                                                 @RequestBody @NotBlank String newTitle, BindingResult bindingResult) {
        log.info("Request patchSchoolByIdName received");
        log.debug("School id: {}", schoolId);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors());
        }
        School school = this.schoolService.patchSchoolByIdTitle(schoolId, newTitle);
        if (school == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        log.info("School name changed");
        log.debug("New school name: {}", school);

        return ResponseEntity.ok(SchoolConverter.convertSchoolEntityToGetResponse(school));
    }

    @GetMapping("/{schoolId}/students")
    public ResponseEntity<List<GetStudentResponse>> getStudentsBySchoolId(@PathVariable Long schoolId) {
        log.info("Request getStudentsBySchoolId received");
        log.debug("Students of school id: {}", schoolId);
        List<Student> students = this.schoolService.getAllStudentsBySchoolId(schoolId);
        if (students == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (students.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        log.info("Students posted");
        log.debug("Students posted: {}", students);
        return ResponseEntity.ok(StudentConverter.convertStudentEntityListToGetResponse(students));
    }

    @PostMapping("/{schoolId}/students")
    public ResponseEntity<?> addStudentToSchoolById(@Valid @RequestBody CreateStudentRequest createStudentRequest, BindingResult bindingResult, @PathVariable Long schoolId) {
        log.info("Request addStudentToSchoolById received");
        log.debug("Post new students to school: {}", schoolId);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ValidationError (bindingResult.getFieldErrors()));
        }
        School school = this.schoolService.addStudentToSchoolBySchoolId(schoolId, createStudentRequest);
        if (school == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        log.info("Student added to school");
        log.debug("Student added to school: {}", school);
        return ResponseEntity.status(HttpStatus.CREATED).body(SchoolConverter.convertSchoolToCreateStudentResponse(school));
    }

    @GetMapping("/{schoolId}/students/{studentId}")
    public ResponseEntity<GetStudentResponse> getStudentBySchoolIdAndStudentId(@PathVariable Long schoolId,
                                                                               @PathVariable Long studentId) {
        log.info("Request getStudentBySchoolIdAndStudentId received");
        log.debug("Get student from school id: {}", schoolId);
        Student student = this.schoolService.getStudentBySchoolIdAndStudentId(schoolId, studentId);

        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        log.info("Selected student posted from selected school");
        log.debug("Student posted: {}", student);
        return ResponseEntity.ok(StudentConverter.convertStudentEntityToGetResponse(student));
    }
}
