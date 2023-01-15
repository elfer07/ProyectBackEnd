package ar.com.morenofernando.d4t.repository;

import ar.com.morenofernando.d4t.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExperienceRepo extends JpaRepository<Experience, Integer> {

    public Optional<Experience> findByNombreE(String nombreE);
    public boolean existsByNombreE(String nombreE);
}
