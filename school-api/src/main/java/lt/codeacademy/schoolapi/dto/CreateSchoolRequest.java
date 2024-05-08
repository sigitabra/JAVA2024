package lt.codeacademy.schoolapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class CreateSchoolRequest {

    @NotBlank(message = "{validation.constraints.not.empty.message}")
    @Size(min = 1, max = 255,message = "{validation.constraints.size.message}")
    private final String title;

    @NotBlank(message = "{validation.constraints.not.empty.message}")
    @Size(min = 1, max = 255,message = "{validation.constraints.size.message}")
    private final String address;

    @NotBlank(message = "{validation.constraints.not.empty.message}")
    @Size(min = 1, max = 255,message = "{validation.constraints.size.message}")
    private final String phoneNo;
}
