package ar.com.morenofernando.d4t.controller;

import ar.com.morenofernando.d4t.dto.DtoSocialNetwork;
import ar.com.morenofernando.d4t.entity.SocialNetwork;
import ar.com.morenofernando.d4t.security.controller.Messages;
import ar.com.morenofernando.d4t.service.SocialNetworkService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/socialnetwork")
@CrossOrigin(origins = "https://portfolioflm.web.app/")
public class SocialNetworkController {

    @Autowired
    SocialNetworkService socialNetworkService;

    @GetMapping("/list")
    public ResponseEntity<List<SocialNetwork>> list() {
        List<SocialNetwork> list = socialNetworkService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<SocialNetwork> getById(@PathVariable("id")int id) {
        if (!socialNetworkService.existsById(id)) {
            return new ResponseEntity(new Messages("No existe ID"), HttpStatus.BAD_REQUEST);
        }

        SocialNetwork socialNetwork = socialNetworkService.getOne(id).get();
        return new ResponseEntity(socialNetwork, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoSocialNetwork dtoSocialNetwork) {
        if(StringUtils.isBlank(dtoSocialNetwork.getName())){
            return new ResponseEntity(new Messages("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(socialNetworkService.existsByName(dtoSocialNetwork.getName())){
            return new ResponseEntity(new Messages("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }

        SocialNetwork socialNetwork = new SocialNetwork(dtoSocialNetwork.getId(), dtoSocialNetwork.getName(), dtoSocialNetwork.getLinkNetwork());
        socialNetworkService.save(socialNetwork);
        return new ResponseEntity(new Messages("Link agregado."), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoSocialNetwork dtoSocialNetwork) {
        if (!socialNetworkService.existsById(id)) {
            return new ResponseEntity(new Messages("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if (socialNetworkService.existsByName(dtoSocialNetwork.getName()) && socialNetworkService.getByName(dtoSocialNetwork.getName()).get().getId() != id) {
            return new ResponseEntity(new Messages("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoSocialNetwork.getName())) {
            return new ResponseEntity(new Messages("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }

        SocialNetwork socialNetwork = socialNetworkService.getOne(id).get();

        socialNetwork.setLinkNetwork(dtoSocialNetwork.getLinkNetwork());

        socialNetworkService.save(socialNetwork);

        return new ResponseEntity(new Messages("Link actualizado"), HttpStatus.OK);
    }
}
