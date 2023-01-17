package ar.com.morenofernando.d4t.repository;

import ar.com.morenofernando.d4t.entity.SocialNetwork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocialNetworkRepo extends JpaRepository<SocialNetwork, Integer> {
    public Optional<SocialNetwork> findByName(String name);
    public boolean existsByName(String name);
}
