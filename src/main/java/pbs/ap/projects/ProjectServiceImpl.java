package pbs.ap.projects;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.jboss.logging.Logger;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService{

    private static final Logger LOG = Logger.getLogger(ProjectServiceImpl.class);
    public List<Project> getAllProjects() {
        LOG.debug(">>>getAllProjects<<<");
        return PanacheEntityBase.listAll();
    }

    public List<Project> getAllUserProjects() {
        return null;
    }

    public Project getProjectById(Long id) {
        return null;
    }

    public Project getProjectByProjectName(String projectName) {
        return null;
    }
}
