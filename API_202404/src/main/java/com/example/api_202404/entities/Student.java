package com.example.api_202404.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    private String gender;

    private String email;

    private String phoneNumber;

    private String address;

    private Long schoolId;

}
