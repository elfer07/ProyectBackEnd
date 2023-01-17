package ar.com.morenofernando.d4t.repository;

import ar.com.morenofernando.d4t.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillRepo extends JpaRepository<Skill, Integer> {
    Optional<Skill> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}
