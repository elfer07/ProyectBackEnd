package ar.com.morenofernando.d4t.security.repository;

import ar.com.morenofernando.d4t.security.enums.UserType;
import ar.com.morenofernando.d4t.security.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolName(UserType rolName);
}
