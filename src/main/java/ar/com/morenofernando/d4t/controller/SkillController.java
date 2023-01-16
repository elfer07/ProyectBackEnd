package ar.com.morenofernando.d4t.controller;

import ar.com.morenofernando.d4t.dto.DtoSkill;
import ar.com.morenofernando.d4t.entity.Skill;
import ar.com.morenofernando.d4t.security.controller.Messages;
import ar.com.morenofernando.d4t.service.SkillService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skill")
@CrossOrigin(origins = "https://portfolioflm.web.app/")
public class SkillController {
    @Autowired
    SkillService skillService;

    @GetMapping("/lista")
    public ResponseEntity<List<Skill>> list() {
        List<Skill> list = skillService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Skill> getById(@PathVariable("id") int id) {
        if (!skillService.existsById(id)) {
            return new ResponseEntity(new Messages("no existe"), HttpStatus.NOT_FOUND);
        }
        Skill hYs = skillService.getOne(id).get();
        return new ResponseEntity(hYs, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!skillService.existsById(id)) {
            return new ResponseEntity(new Messages("no existe"), HttpStatus.NOT_FOUND);
        }
        skillService.delete(id);
        return new ResponseEntity(new Messages("Skill eliminado"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoSkill dtoSkill) {
        if (StringUtils.isBlank(dtoSkill.getNombre())) {
            return new ResponseEntity(new Messages("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (skillService.existsByNombre(dtoSkill.getNombre())) {
            return new ResponseEntity(new Messages("Esa skill ya existe"), HttpStatus.BAD_REQUEST);
        }

        Skill hYs = new Skill(dtoSkill.getNombre(), dtoSkill.getPorcentaje());
        skillService.save(hYs);

        return new ResponseEntity(new Messages("Skill agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoSkill dtoSkill) {
        //Validamos si existe el ID
        if (!skillService.existsById(id)) {
            return new ResponseEntity(new Messages("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
        //Compara nombre de skills
        if (skillService.existsByNombre(dtoSkill.getNombre()) && skillService.getByNombre(dtoSkill.getNombre()).get()
                .getId() != id) {
            return new ResponseEntity(new Messages("Esa skill ya existe"), HttpStatus.BAD_REQUEST);
        }
        //No puede estar vacio
        if (StringUtils.isBlank(dtoSkill.getNombre())) {
            return new ResponseEntity(new Messages("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Skill hYs = skillService.getOne(id).get();
        hYs.setNombre(dtoSkill.getNombre());
        hYs.setPorcentaje(dtoSkill.getPorcentaje());

        skillService.save(hYs);
        return new ResponseEntity(new Messages("Skill actualizada"), HttpStatus.OK);

    }
}
