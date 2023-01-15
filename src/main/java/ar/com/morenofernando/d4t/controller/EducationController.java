package ar.com.morenofernando.d4t.controller;

import ar.com.morenofernando.d4t.dto.DtoEducation;
import ar.com.morenofernando.d4t.entity.Education;
import ar.com.morenofernando.d4t.security.controller.Messages;
import ar.com.morenofernando.d4t.service.EducationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/education")
@CrossOrigin(origins = "http://localhost:4200")
public class EducationController {

    @Autowired
    EducationService sEducacion;

    @GetMapping("/lista")
    public ResponseEntity<List<Education>> list(){
        List<Education> list = sEducacion.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Education> getById(@PathVariable("id")int id){
        if(!sEducacion.existsById(id)){
            return new ResponseEntity(new Messages("No existe el ID"), HttpStatus.BAD_REQUEST);
        }

        Education educacion = sEducacion.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!sEducacion.existsById(id)){
            return new ResponseEntity(new Messages("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        sEducacion.delete(id);
        return new ResponseEntity(new Messages("Educacion eliminada"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoEducation dtoeducacion){
        if(StringUtils.isBlank(dtoeducacion.getNombreE())){
            return new ResponseEntity(new Messages("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(sEducacion.existsByNombreE(dtoeducacion.getNombreE())){
            return new ResponseEntity(new Messages("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }

        Education educacion = new Education(
                dtoeducacion.getNombreE(), dtoeducacion.getDescripcionE()
        );
        sEducacion.save(educacion);
        return new ResponseEntity(new Messages("Educacion creada"), HttpStatus.OK);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoEducation dtoeducacion){
        if(!sEducacion.existsById(id)){
            return new ResponseEntity(new Messages("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if(sEducacion.existsByNombreE(dtoeducacion.getNombreE()) && sEducacion.getByNmbreE(dtoeducacion.getNombreE()).get().getId() != id){
            return new ResponseEntity(new Messages("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoeducacion.getNombreE())){
            return new ResponseEntity(new Messages("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }

        Education educacion = sEducacion.getOne(id).get();

        educacion.setNombreE(dtoeducacion.getNombreE());
        educacion.setDescripcionE(dtoeducacion.getDescripcionE());

        sEducacion.save(educacion);

        return new ResponseEntity(new Messages("Educacion actualizada"), HttpStatus.OK);
    }
}
