package ar.com.morenofernando.d4t.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class SocialNetwork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 3, max = 50, message = "no cumple con la longitud")
    private String name;

    private String linkNetwork;

    public SocialNetwork() {

    }

    public SocialNetwork(int id, String name, String linkNetwork) {
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
