package ar.com.morenofernando.d4t.service;

import ar.com.morenofernando.d4t.entity.Person;
import ar.com.morenofernando.d4t.interfaces.IPersonService;
import ar.com.morenofernando.d4t.repository.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpPersonService implements IPersonService {

    @Autowired
    IPersonRepository iPersonRepository;

    @Override
    public List<Person> getPerson() {
        return iPersonRepository.findAll();
    }

    @Override
    public void savePerson(Person person) {
        iPersonRepository.save(person);
    }

    @Override
    public void deletePerson(Long id) {
        iPersonRepository.deleteById(id);
    }

    @Override
    public Person findPerson(Long id) {
        return iPersonRepository.findById(id).orElse(null);
    }
}
