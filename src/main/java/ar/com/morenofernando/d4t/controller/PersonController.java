package ar.com.morenofernando.d4t.controller;

import ar.com.morenofernando.d4t.dto.DtoPerson;
import ar.com.morenofernando.d4t.entity.Person;
import ar.com.morenofernando.d4t.security.controller.Messages;
import ar.com.morenofernando.d4t.service.ImpPersonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://portfolioflm.web.app/")
public class PersonController {

    @Autowired
    ImpPersonService iPersonService;

    @GetMapping("/lista")
    public ResponseEntity<List<Person>> list(){
        List<Person> list = iPersonService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Person> getById(@PathVariable("id")int id){
        if(!iPersonService.existsById(id)){
            return new ResponseEntity(new Messages("No existe el ID"), HttpStatus.BAD_REQUEST);
        }

        Person educacion = iPersonService.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }

    /*@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!iPersonService.existsById(id)){
            return new ResponseEntity(new Messages("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        iPersonService.delete(id);
        return new ResponseEntity(new Messages("Persona eliminada"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoPerson dtoPerson){
        if(StringUtils.isBlank(dtoPerson.getName())){
            return new ResponseEntity(new Messages("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(iPersonService.existsByName(dtoPerson.getName())){
            return new ResponseEntity(new Messages("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }

        Person person = new Person(
                dtoPerson.getName(), dtoPerson.getDescription()
        );
        iPersonService.save(person);
        return new ResponseEntity(new Messages("Persona creada"), HttpStatus.OK);

    }*/

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoPerson dtoPerson){
        if(!iPersonService.existsById(id)){
            return new ResponseEntity(new Messages("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if(iPersonService.existsByName(dtoPerson.getName()) && iPersonService.getByName(dtoPerson.getName()).get().getId() != id){
            return new ResponseEntity(new Messages("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoPerson.getName())){
            return new ResponseEntity(new Messages("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }

        Person person = iPersonService.getOne(id).get();

        person.setName(dtoPerson.getName());
        person.setSurname(dtoPerson.getSurname());
        person.setDescription(dtoPerson.getDescription());
        person.setImg(dtoPerson.getImg());

        iPersonService.save(person);

        return new ResponseEntity(new Messages("Persona actualizada"), HttpStatus.OK);
    }
}
