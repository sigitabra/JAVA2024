package com.example.api_202404.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class StudentDTOInput {

    private final String firstName;

    private final String lastName;

    private final Date dateOfBirth;

    private final String gender;

    private final String email;

    private final String phoneNumber;

    private final String address;

    private final Long schoolId;
}
