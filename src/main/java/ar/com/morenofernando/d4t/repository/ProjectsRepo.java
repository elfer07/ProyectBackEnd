package ar.com.morenofernando.d4t.repository;

import ar.com.morenofernando.d4t.entity.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectsRepo extends JpaRepository<Projects, Integer> {
    public Optional<Projects> findByTitle(String title);
    public boolean existsByTitle(String title);
}
