package ar.com.morenofernando.d4t.dto;

import javax.validation.constraints.NotBlank;

public class DtoSocialNetwork {

    @NotBlank
    private int id;
    @NotBlank
    private String name;
    @NotBlank
    private String linkNetwork;

    public DtoSocialNetwork() {

    }

    public DtoSocialNetwork(int id, String name, String linkNetwork) {
        this.id = id;
        this.name = name;
        this.linkNetwork = linkNetwork;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkNetwork() {
        return linkNetwork;
    }

    public void setLinkNetwork(String linkNetwork) {
        this.linkNetwork = linkNetwork;
    }
}
