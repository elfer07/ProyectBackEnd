package ar.com.morenofernando.d4t.service;

import ar.com.morenofernando.d4t.entity.Person;
import ar.com.morenofernando.d4t.repository.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ImpPersonService {

    @Autowired
    IPersonRepository iPersonRepository;

    public List<Person> list() {
        return iPersonRepository.findAll();
    }

    public Optional<Person> getOne(int id) {
        return iPersonRepository.findById(id);
    }

    public Optional<Person> getByName(String name) {
        return iPersonRepository.findByName(name);
    }

    public void save(Person person) {
        iPersonRepository.save(person);
    }

    public void delete(int id) {
        iPersonRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return iPersonRepository.existsById(id);
    }

    public boolean existsByName(String name) {
        return  iPersonRepository.existsByName(name);
    }
}
