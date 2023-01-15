package ar.com.morenofernando.d4t.security.controller;

import ar.com.morenofernando.d4t.security.dto.JwtDto;
import ar.com.morenofernando.d4t.security.dto.LoginUser;
import ar.com.morenofernando.d4t.security.dto.NewUser;
import ar.com.morenofernando.d4t.security.enums.UserType;
import ar.com.morenofernando.d4t.security.jwt.JwtProvider;
import ar.com.morenofernando.d4t.security.model.Rol;
import ar.com.morenofernando.d4t.security.model.User;
import ar.com.morenofernando.d4t.security.service.RolService;
import ar.com.morenofernando.d4t.security.service.UserService;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/new")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NewUser newUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Messages("Campos mal puestos o email invalido"), HttpStatus.BAD_REQUEST);

        if (userService.existsByUsername(newUser.getUsername()))
            return new ResponseEntity(new Messages("Nombre de usuario existe!"), HttpStatus.BAD_REQUEST);

        if (userService.existsByEmail(newUser.getEmail()))
            return new ResponseEntity(new Messages("Email existe!"), HttpStatus.BAD_REQUEST);

        User user = new User(newUser.getName(), newUser.getUsername(), newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()));

        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolName(UserType.USER).get());

        if (newUser.getRoles().contains("admin"))
            roles.add(rolService.getByRolName(UserType.ADMIN).get());

        user.setUsers(roles);
        userService.save(user);

        return new ResponseEntity(new Messages("Usuario guardado!"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Messages("Campos mal puestos o email invalido"), HttpStatus.BAD_REQUEST);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginUser.getUsername(), loginUser.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());

        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }

}
