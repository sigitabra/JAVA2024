package lt.codeacademy.schoolapi.converters;

import lt.codeacademy.schoolapi.dto.GetStudentResponse;
import lt.codeacademy.schoolapi.entities.Student;

import java.util.ArrayList;
import java.util.List;

public abstract class StudentConverter {

    private StudentConverter() {}

    public static GetStudentResponse convertStudentEntityToGetResponse(Student student) {
        GetStudentResponse getStudentResponse = null;
        if (student != null) {
            getStudentResponse = new GetStudentResponse();
            getStudentResponse.setId(student.getId());
            getStudentResponse.setName(student.getName());
            getStudentResponse.setLastname(student.getLastname());
            getStudentResponse.setBirthdate(student.getBirthdate());
            getStudentResponse.setPhoneNo(student.getPhoneNo());
        }
        return getStudentResponse;
    }

    public static List<GetStudentResponse> convertStudentEntityListToGetResponse(List<Student> students) {
        List<GetStudentResponse> studentResponses = null;
        if (students != null) {
            studentResponses = new ArrayList<>();
            for (Student student : students) {
                studentResponses.add(convertStudentEntityToGetResponse(student));
            }
        }
        return studentResponses;
    }
}
