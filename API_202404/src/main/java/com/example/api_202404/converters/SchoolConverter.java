package com.example.api_202404.converters;

import com.example.api_202404.dto.SchoolDTOInput;
import com.example.api_202404.dto.SchoolDTOOutput;
import com.example.api_202404.entities.School;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SchoolConverter {

    public static SchoolDTOOutput convertEntityToDTO(School school) {
        SchoolDTOOutput schoolDTOOutput = null;
        if (school != null) {
            schoolDTOOutput = new SchoolDTOOutput();
            schoolDTOOutput.setId(school.getId());
            schoolDTOOutput.setName(school.getName());
            schoolDTOOutput.setAddress(school.getAddress());
        }
        return schoolDTOOutput;
    }

    public static List<SchoolDTOOutput> convertEntityListToDTO(List<School> schools) {
        List<SchoolDTOOutput> schoolDTOOutputList = null;
        if (schools != null && !schools.isEmpty()) {
            schoolDTOOutputList = new ArrayList<>();
            for (School school : schools) {
                schoolDTOOutputList.add(convertEntityToDTO(school));
            }
        }
        return schoolDTOOutputList;
    }

    public static School convertDTOToEntity(SchoolDTOInput schoolDTO) {
        School school = null;
        if (schoolDTO != null) {
            school = new School();
            school.setName(schoolDTO.getName());
            school.setAddress(schoolDTO.getAddress());
            school.setCreatedAt(Date.valueOf(LocalDate.now()));
            school.setUpdatedAt(Date.valueOf(LocalDate.now()));
        }
        return school;
    }
}
