package ar.com.morenofernando.d4t.service;

import ar.com.morenofernando.d4t.entity.Education;
import ar.com.morenofernando.d4t.repository.EducationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EducationService {

    @Autowired
    EducationRepo rEducacion;

    public List<Education> list(){
        return rEducacion.findAll();
    }

    public Optional<Education> getOne(int id){
        return rEducacion.findById(id);
    }

    public Optional<Education> getByNmbreE(String nombreE){
        return rEducacion.findByNombreE(nombreE);
    }

    public void save(Education educacion){
        rEducacion.save(educacion);
    }

    public void delete(int id){
        rEducacion.deleteById(id);
    }

    public boolean existsById(int id){
        return rEducacion.existsById(id);
    }

    public boolean existsByNombreE(String nombreE){
        return rEducacion.existsByNombreE(nombreE);
    }
}
