package lt.codeacademy.schoolapi.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class GetStudentResponse {
    private Long id;
    private String name;
    private String lastname;
    private Date birthdate;
    private String phoneNo;
}
