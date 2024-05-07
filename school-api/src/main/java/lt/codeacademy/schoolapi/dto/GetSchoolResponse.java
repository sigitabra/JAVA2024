package lt.codeacademy.schoolapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetSchoolResponse {
    private Long id;
    private String title;
    private String address;
    private String phoneNo;
}
