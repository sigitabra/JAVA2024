package lt.codeacademy.schoolapi.converters;

import lt.codeacademy.schoolapi.dto.CreateSchoolResponse;
import lt.codeacademy.schoolapi.dto.CreateStudentResponse;
import lt.codeacademy.schoolapi.dto.GetSchoolResponse;
import lt.codeacademy.schoolapi.entities.School;

import java.util.ArrayList;
import java.util.List;

public abstract class SchoolConverter {

    private SchoolConverter() {
    }

    public static GetSchoolResponse convertSchoolEntityToGetResponse(School school) {
        GetSchoolResponse getSchoolResponse = null;
        if (school != null) {
            getSchoolResponse = new GetSchoolResponse();
            getSchoolResponse.setId(school.getId());
            getSchoolResponse.setTitle(school.getTitle());
            getSchoolResponse.setAddress(school.getAddress());
            getSchoolResponse.setPhoneNo(school.getPhoneNo());
        }
        return getSchoolResponse;
    }

    public static CreateSchoolResponse convertSchoolEntityToCreateResponse(School school) {
        return new CreateSchoolResponse(convertSchoolEntityToGetResponse(school));
    }

    public static List<GetSchoolResponse> convertSchoolEntityListToGetResponse(List<School> schools) {
        List<GetSchoolResponse> schoolResponses = null;
        if (schools != null) {
            schoolResponses = new ArrayList<>();
            for (School school : schools) {
                schoolResponses.add(convertSchoolEntityToGetResponse(school));
            }
        }
        return schoolResponses;
    }

    public static CreateStudentResponse convertSchoolToCreateStudentResponse(School school) {
        CreateStudentResponse createStudentResponse = null;
        if (school != null) {
            createStudentResponse =
                    new CreateStudentResponse(SchoolConverter.convertSchoolEntityToGetResponse(school),
                            StudentConverter.convertStudentEntityListToGetResponse(school.getStudents()));
        }
        return createStudentResponse;
    }
}
