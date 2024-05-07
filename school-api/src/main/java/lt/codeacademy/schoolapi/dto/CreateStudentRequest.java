package lt.codeacademy.schoolapi.dto;

import lombok.Data;

import java.sql.Date;


@Data
public class CreateStudentRequest {
    private final String name;
    private final String lastname;
    private final Date birthDate;
    private final String phoneNo;
}
