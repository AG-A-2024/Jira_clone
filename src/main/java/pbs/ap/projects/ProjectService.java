package pbs.ap.projects;

import jakarta.ws.rs.core.Response;

import java.util.List;

public interface ProjectService {
    List<Project> getAllProjects();
    List<Project> getAllUserProjects();
    Project getProjectById(Long id);
    Project getProjectByProjectName(String projectName);
}
