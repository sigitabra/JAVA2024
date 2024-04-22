package com.example.api_202404.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class StudentDTOOutgoing {
    private Long id;

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    private String gender;

    private Long schoolId;
}
