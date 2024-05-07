package lt.codeacademy.schoolapi.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CreateSchoolResponse extends GetSchoolResponse {
    public CreateSchoolResponse(GetSchoolResponse getSchoolResponse) {
        super(getSchoolResponse.getId(),
                getSchoolResponse.getTitle(),
                getSchoolResponse.getAddress(),
                getSchoolResponse.getPhoneNo());
    }
}
