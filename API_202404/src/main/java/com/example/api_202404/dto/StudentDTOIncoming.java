package com.example.api_202404.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class StudentDTOIncoming {

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    private String gender;

    private String email;

    private String phoneNumber;

    private String address;

    private Long schoolId;
}
