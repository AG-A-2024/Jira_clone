package pbs.ap.projects;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService{

    private static final Logger LOG = Logger.getLogger(ProjectServiceImpl.class);
    public List<Project> getAllProjects() {
        LOG.debug(">>>getAllProjects<<<");
        return Project.listAll();
    }

    public List<Project> getAllUserProjects() {
        return null;
    }

    public Optional<Project> getProjectById(Long id) {
        LOG.debug(">>>getProjectById<<<");
        return Project.findByIdOptional(id);
    }

    public Optional<Project> getProjectByProjectName(String projectName) {
        LOG.debug(">>>getProjectByProjectName<<<");
        return Project.find("projectName", projectName).firstResultOptional();
    }

    @Transactional
    public boolean addProject(Project project){
        LOG.debug(">>>addProject " + project.toString() + "<<<");
        project.persistAndFlush();
        return project.isPersistent();
    }

    @Transactional
    public boolean deleteProjectById(Long id){
        return Project.deleteById(id);
    }
}
