package ar.com.morenofernando.d4t.security.service;

import ar.com.morenofernando.d4t.security.model.User;
import ar.com.morenofernando.d4t.security.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    IUserRepo iUserRepo;

    public Optional<User> getByUsername(String username) {
        return iUserRepo.findByUsername(username);
    }

    public boolean existsByUsername(String username) {
        return iUserRepo.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return iUserRepo.existsByEmail(email);
    }

    public void save(User user) {
        iUserRepo.save(user);
    }
}
