package lt.codeacademy.schoolapi.repositories;

import lt.codeacademy.schoolapi.entities.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
}
