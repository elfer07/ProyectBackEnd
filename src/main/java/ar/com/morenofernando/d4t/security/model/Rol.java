package ar.com.morenofernando.d4t.security.model;

import ar.com.morenofernando.d4t.security.enums.UserType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserType rolName;

    public Rol() {

    }

    public Rol(UserType rolName) {
        this.rolName = rolName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserType getRolName() {
        return rolName;
    }

    public void setRolName(UserType rolName) {
        this.rolName = rolName;
    }
}
