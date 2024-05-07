package lt.codeacademy.schoolapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class CreateSchoolRequest {
    private final String title;
    private final String address;
    private final String phoneNo;
}
