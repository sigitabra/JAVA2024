package com.example.api_202404.converters;

import com.example.api_202404.dto.SchoolDTOIncoming;
import com.example.api_202404.dto.SchoolDTOOutgoing;
import com.example.api_202404.entities.School;

import java.util.ArrayList;
import java.util.List;

public class SchoolConverter {
    public static SchoolDTOOutgoing convertEntityToDTO(School school) {
        SchoolDTOOutgoing schoolDTOOutgoing = null;
        if (school != null) {
            schoolDTOOutgoing = new SchoolDTOOutgoing();
            schoolDTOOutgoing.setId(school.getId());
            schoolDTOOutgoing.setName(school.getName());
            schoolDTOOutgoing.setAddress(school.getAddress());
            schoolDTOOutgoing.setStudents(StudentConverter.convertEntityListToDTO(school.getStudents()));
        }
        return schoolDTOOutgoing;
    }

    public static List<SchoolDTOOutgoing> convertEntityListToDTO(List<School> schools) {
        List<SchoolDTOOutgoing> schoolDTOOutgoingList = null;
        if (schools != null && !schools.isEmpty()) {
            schoolDTOOutgoingList = new ArrayList<>();
            for (School school : schools) {
                schoolDTOOutgoingList.add(convertEntityToDTO(school));
            }
        }
        return schoolDTOOutgoingList;
    }

    public static School convertDTOToEntity(SchoolDTOIncoming schoolDTO) {
        School school = null;
        if (schoolDTO != null) {
            school = new School(schoolDTO.getName(), schoolDTO.getAddress());
        }
        return school;
    }
}
