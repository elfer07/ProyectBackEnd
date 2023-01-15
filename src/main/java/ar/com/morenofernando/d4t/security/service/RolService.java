package ar.com.morenofernando.d4t.security.service;

import ar.com.morenofernando.d4t.security.enums.UserType;
import ar.com.morenofernando.d4t.security.model.Rol;
import ar.com.morenofernando.d4t.security.repository.IRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    IRolRepository iRolRepository;

    public Optional<Rol> getByRolName(UserType rolName) {
        return iRolRepository.findByRolName(rolName);
    }

    public void save(Rol rol) {
        iRolRepository.save(rol);
    }
}
