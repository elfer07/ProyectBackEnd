package ar.com.morenofernando.d4t.service;

import ar.com.morenofernando.d4t.entity.Skill;
import ar.com.morenofernando.d4t.repository.SkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class SkillService {
    @Autowired
    SkillRepo rhys;

    public List<Skill> list(){
        return rhys.findAll();
    }

    public Optional<Skill> getOne(int id){
        return rhys.findById(id);
    }

    public Optional<Skill> getByNombre(String nombre){
        return rhys.findByNombre(nombre);
    }

    public void save(Skill skill){
        rhys.save(skill);
    }

    public void delete(int id){
        rhys.deleteById(id);
    }

    public boolean existsById(int id){
        return rhys.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return rhys.existsByNombre(nombre);
    }
}
