package ar.com.morenofernando.d4t.service;

import ar.com.morenofernando.d4t.entity.Projects;
import ar.com.morenofernando.d4t.repository.ProjectsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjectsService {

    @Autowired
    ProjectsRepo projectsRepo;

    public List<Projects> list() {
        return projectsRepo.findAll();
    }

    public Optional<Projects> getOne(int id) {
        return projectsRepo.findById(id);
    }

    public Optional<Projects> getByName(String title) {
        return projectsRepo.findByTitle(title);
    }

    public void save(Projects projects) {
        projectsRepo.save(projects);
    }

    public void delete(int id) {
        projectsRepo.deleteById(id);
    }

    public boolean existsById(int id) {
        return projectsRepo.existsById(id);
    }

    public boolean existsByName(String title) {
        return  projectsRepo.existsByTitle(title);
    }
}
