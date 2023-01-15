package ar.com.morenofernando.d4t.repository;

import ar.com.morenofernando.d4t.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EducationRepo extends JpaRepository<Education, Integer> {
    public Optional<Education> findByNombreE(String nombreE);
    public boolean existsByNombreE(String nombreE);
}
