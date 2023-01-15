package ar.com.morenofernando.d4t.repository;

import ar.com.morenofernando.d4t.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkillRepo extends JpaRepository<Skill, Integer> {
    Optional<Skill> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}
