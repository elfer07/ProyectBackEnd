package ar.com.morenofernando.d4t.service;

import ar.com.morenofernando.d4t.entity.SocialNetwork;
import ar.com.morenofernando.d4t.repository.SocialNetworkRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SocialNetworkService {

    @Autowired
    SocialNetworkRepo socialNetworkRepo;

    public List<SocialNetwork> list() {
        return socialNetworkRepo.findAll();
    }

    public Optional<SocialNetwork> getOne(int id) {
        return socialNetworkRepo.findById(id);
    }

    public Optional<SocialNetwork> getByName(String name) {
        return socialNetworkRepo.findByName(name);
    }

    public void save(SocialNetwork socialNetwork) {
        socialNetworkRepo.save(socialNetwork);
    }

    public void delete(int id) {
        socialNetworkRepo.deleteById(id);
    }

    public boolean existsById(int id) {
        return socialNetworkRepo.existsById(id);
    }

    public boolean existsByName(String name) {
        return  socialNetworkRepo.existsByName(name);
    }
}
