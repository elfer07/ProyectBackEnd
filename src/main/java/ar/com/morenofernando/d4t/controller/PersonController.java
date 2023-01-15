package ar.com.morenofernando.d4t.controller;

import ar.com.morenofernando.d4t.entity.Person;
import ar.com.morenofernando.d4t.interfaces.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PersonController {

    @Autowired
    IPersonService iPersonService;

    @GetMapping("persons/fetch")
    public List<Person> getPerson() {
        return iPersonService.getPerson();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/persons/create")
    public String createPerson(@RequestBody Person person) {
        iPersonService.savePerson(person);
        return "Persona creada con éxito!";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/persons/delete/{id}")
    public String deletePerson(@PathVariable Long id) {
        iPersonService.deletePerson(id);
        return "Persona borrada con éxito!";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/persons/edit/{id}")
    public Person editPerson(@PathVariable Long id,
                             @RequestParam("name") String newName,
                             @RequestParam("surname") String newSurname,
                             @RequestParam("img") String newImg) {
        Person person = iPersonService.findPerson(id);
        person.setName(newName);
        person.setSurname(newSurname);
        person.setImg(newImg);

        iPersonService.savePerson(person);
        return person;
    }

    @GetMapping("/persons/fetch/profile")
    public Person findPerson() {
        long ids = 1;
        return iPersonService.findPerson(ids);
    }
}
