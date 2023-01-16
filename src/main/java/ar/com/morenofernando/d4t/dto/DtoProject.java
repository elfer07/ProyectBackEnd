package ar.com.morenofernando.d4t.dto;

import javax.validation.constraints.NotBlank;

public class DtoProject {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String img;

    public DtoProject() {
    }

    public DtoProject(String title, String description, String img) {
        this.title = title;
        this.description = description;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
