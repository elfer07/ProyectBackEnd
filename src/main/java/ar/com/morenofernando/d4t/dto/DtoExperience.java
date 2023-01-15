package ar.com.morenofernando.d4t.dto;

import javax.validation.constraints.NotBlank;

public class DtoExperience {

    @NotBlank
    private String name;

    @NotBlank
    private String descripcionE;


    public DtoExperience() {
    }

    public DtoExperience(String nombreE, String descripcionE) {
        this.name = nombreE;
        this.descripcionE = descripcionE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcionE() {
        return descripcionE;
    }

    public void setDescripcionE(String descripcionE) {
        this.descripcionE = descripcionE;
    }
}
