package lt.codeacademy.schoolapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Date;


@Data
public class CreateStudentRequest {
    @NotBlank(message = "{validation.constraints.not.empty.message}")
    @Size(min = 1, max = 255,message = "{validation.constraints.size.message}")
    private final String name;

    @NotBlank(message = "{validation.constraints.not.empty.message}")
    @Size(min = 1, max = 255,message = "{validation.constraints.size.message}")
    private final String lastname;

    @Past(message = "{validation.constraints.past.message}")
    private final Date birthDate;

    @NotBlank(message = "{validation.constraints.not.empty.message}")
    @Size(min = 1, max = 255,message = "{validation.constraints.size.message}")
    private final String phoneNo;
}
