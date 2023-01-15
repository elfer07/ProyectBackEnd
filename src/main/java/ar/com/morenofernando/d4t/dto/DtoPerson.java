package ar.com.morenofernando.d4t.dto;

import javax.validation.constraints.NotBlank;

public class DtoPerson {

    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String description;
    @NotBlank
    private String img;

    public DtoPerson() {
    }

    public DtoPerson(String nombre, String apellido, String descripcion, String img) {
        this.name = nombre;
        this.surname = apellido;
        this.description = descripcion;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
