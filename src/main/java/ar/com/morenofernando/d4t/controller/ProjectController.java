package ar.com.morenofernando.d4t.controller;

import ar.com.morenofernando.d4t.dto.DtoProject;
import ar.com.morenofernando.d4t.entity.Projects;
import ar.com.morenofernando.d4t.security.controller.Messages;
import ar.com.morenofernando.d4t.service.ProjectsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://portfolioflm.web.app/")
public class ProjectController {

    @Autowired
    ProjectsService projectsService;

    @GetMapping("/lista")
    public ResponseEntity<List<Projects>> list(){
        List<Projects> list = projectsService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Projects> getById(@PathVariable("id")int id){
        if(!projectsService.existsById(id)){
            return new ResponseEntity(new Messages("No existe el ID"), HttpStatus.BAD_REQUEST);
        }

        Projects projects = projectsService.getOne(id).get();
        return new ResponseEntity(projects, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!projectsService.existsById(id)){
            return new ResponseEntity(new Messages("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        projectsService.delete(id);
        return new ResponseEntity(new Messages("Proyecto eliminada"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoProject dtoProject){
        if(StringUtils.isBlank(dtoProject.getTitle())){
            return new ResponseEntity(new Messages("El titulo es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(projectsService.existsByName(dtoProject.getTitle())){
            return new ResponseEntity(new Messages("Ese titulo ya existe"), HttpStatus.BAD_REQUEST);
        }

        Projects projects = new Projects(
                dtoProject.getTitle(), dtoProject.getDescription(), dtoProject.getImage()
        );
        projectsService.save(projects);
        return new ResponseEntity(new Messages("Proyecto creado"), HttpStatus.OK);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoProject dtoProject){
        if(!projectsService.existsById(id)){
            return new ResponseEntity(new Messages("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if(projectsService.existsByName(dtoProject.getTitle()) && projectsService.getByName(dtoProject.getTitle()).get().getId() != id){
            return new ResponseEntity(new Messages("Ese titulo ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoProject.getTitle())){
            return new ResponseEntity(new Messages("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }

        Projects projects = projectsService.getOne(id).get();

        projects.setTitle(dtoProject.getTitle());
        projects.setDescription(dtoProject.getDescription());
        projects.setImage(dtoProject.getImage());

        projectsService.save(projects);

        return new ResponseEntity(new Messages("Proyecto actualizado"), HttpStatus.OK);
    }
}
