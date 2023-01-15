package ar.com.morenofernando.d4t.service;

import ar.com.morenofernando.d4t.entity.Experience;
import ar.com.morenofernando.d4t.repository.ExperienceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ExperienceService {

    @Autowired
    ExperienceRepo experienceRepo;

    public List<Experience> list() {
        return experienceRepo.findAll();
    }

    public Optional<Experience> getOne(int id) {
        return experienceRepo.findById(id);
    }

    public Optional<Experience> getByNombreE(String nombreE) {
        return experienceRepo.findByNombreE(nombreE);
    }

    public void save(Experience experience) {
        experienceRepo.save(experience);
    }

    public void delete(int id) {
        experienceRepo.deleteById(id);
    }

    public boolean existsById(int id) {
        return experienceRepo.existsById(id);
    }

    public boolean existsByNombreE(String nombreE) {
        return  experienceRepo.existsByNombreE(nombreE);
    }
}
