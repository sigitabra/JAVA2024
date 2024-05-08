package lt.codeacademy.schoolapi.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.codeacademy.schoolapi.dto.CreateSchoolRequest;
import lt.codeacademy.schoolapi.dto.CreateStudentRequest;
import lt.codeacademy.schoolapi.entities.School;
import lt.codeacademy.schoolapi.entities.Student;
import lt.codeacademy.schoolapi.repositories.SchoolRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;


    public List<School> getAllSchools() {
        log.info("START: getAllSchools");
        List<School> schools = this.schoolRepository.findAll();
        if (schools.isEmpty()) {
            log.info("END: no schools found");
            return new ArrayList<>();
        }
        log.info("END: schools found");
        return this.schoolRepository.findAll();
    }

    public School getSchoolById(Long schoolId) {
        log.info("START: getSchoolById with schoolId: {}", schoolId);
        School school = this.schoolRepository.findById(schoolId).orElse(null);
        if (school == null) {
            log.info("END: school not found by school id: {}", schoolId);
            return null;
        }
        log.info("END: school found by school id: {}", schoolId);
        return school;
    }

    public List<Student> getAllStudentsBySchoolId(Long schoolId) {
        log.info("START: getAllStudentsBySchoolId with schoolId: {}", schoolId);
        School school = getSchoolById(schoolId);
        if (school != null) {
            log.info("END: students found by school id: {}", schoolId);
            return school.getStudents();
        }
        log.info("END: no students found by school id: {}", schoolId);
        return null;
    }

    public Student getStudentBySchoolIdAndStudentId(Long schoolId, Long studentId) {
//        School school = getSchoolById(schoolId);
//        for (Student student : school.getStudents()) {
//            if (student.getId().equals(studentId)) {
//                return student;
//            }
//        }
//        return null;
        log.info("START: getStudentBySchoolIdAndStudentId with schoolId: {} and studentId {}", schoolId, studentId);
        School school = getSchoolById(schoolId);

        if (school == null) {
            log.info("END: school not found by school id: {}", schoolId);
            return null;
        }

        Student result = school.getStudents().stream()
                .filter(student -> student.getId().equals(studentId))
                .findFirst()
                .orElse(null);

        if (result == null) {
            log.info("END: student not found by schoolId: {} and studentId {}", schoolId, studentId);
            return null;
        }
        log.info("END: student found by schoolId: {} and studentId {}", schoolId, studentId);
        return result;
    }

    public School addSchool(CreateSchoolRequest createSchoolRequest) {
        log.info("START: getStudentBySchoolIdAndStudentId");
        School school = new School();
        school.setTitle(createSchoolRequest.getTitle());
        school.setAddress(createSchoolRequest.getAddress());
        school.setPhoneNo(createSchoolRequest.getPhoneNo());
        school.setStudents(new ArrayList<>());
        log.info("END: school added: {}", school);
        return this.schoolRepository.saveAndFlush(school);
    }

    public School addStudentToSchoolBySchoolId(Long schoolId, CreateStudentRequest createStudentRequest) {
        log.info("START: addStudentToSchoolBySchoolId");
        School school = getSchoolById(schoolId);
        if (school != null) {
            Student student = new Student();
            student.setName(createStudentRequest.getName());
            student.setLastname(createStudentRequest.getLastname());
            student.setBirthdate(createStudentRequest.getBirthDate());
            student.setPhoneNo(createStudentRequest.getPhoneNo());

            school.getStudents().add(student);
            log.info("END: student added: {}", student);
            return this.schoolRepository.saveAndFlush(school);
        }
        log.info("END: school not found by id: {}", schoolId);
        return school;
    }

    public School patchSchoolByIdTitle(Long id, String newTitle) {
        log.info("START: patchSchoolByIdTitle");
        School school = getSchoolById(id);
        if (school != null) {
            school.setTitle(newTitle);
            log.info("END: school id: {},Name changed to: {}", id, school.getTitle());
            return this.schoolRepository.saveAndFlush(school);
        }
        log.info("END: school not found by id: {}", id);
        return school;
    }
}
