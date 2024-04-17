package com.example.api_202404.repositories;

import com.example.api_202404.entities.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
}
