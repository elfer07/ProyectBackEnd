package ar.com.morenofernando.d4t.dto;

import javax.validation.constraints.NotBlank;

public class DtoExperience {

    @NotBlank
    private String nombreE;

    @NotBlank
    private String descripcionE;


    public DtoExperience() {
    }

    public DtoExperience(String nombreE, String descripcionE) {
        this.nombreE = nombreE;
        this.descripcionE = descripcionE;
    }

    public String getNombreE() {
        return nombreE;
    }

    public void setNombreE(String nombreE) {
        this.nombreE = nombreE;
    }

    public String getDescripcionE() {
        return descripcionE;
    }

    public void setDescripcionE(String descripcionE) {
        this.descripcionE = descripcionE;
    }
}
