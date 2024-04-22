package pbs.ap.projects;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    List<Project> getAllProjects();
    List<Project> getAllUserProjects();
    Optional<Project> getProjectById(Long id);
    Optional<Project> getProjectByProjectName(String projectName);
    boolean addProject(Project project);
    boolean deleteProjectById(Long id);
    Project updateProjectById(Long id, Project project);
}
