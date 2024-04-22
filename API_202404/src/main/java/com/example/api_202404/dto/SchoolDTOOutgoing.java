package com.example.api_202404.dto;

import com.example.api_202404.entities.Student;
import lombok.Data;

import java.util.List;

@Data
public class SchoolDTOOutgoing {
    private Long id;
    private String name;
    private String address;
    private List<StudentDTOOutgoing> students;
}
