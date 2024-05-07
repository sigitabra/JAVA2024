package lt.codeacademy.schoolapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateStudentResponse extends CreateSchoolResponse {
    private final List<GetStudentResponse> students;

    public CreateStudentResponse(GetSchoolResponse getSchoolResponse, List<GetStudentResponse> students) {
        super(getSchoolResponse);
        this.students = students;
    }
}
