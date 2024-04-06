package pbs.ap.projects;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;


@ApplicationScoped
@Path("/projects")
@Produces({"application/json","application/problem+json"})
@RolesAllowed({"ADMIN", "USER"})
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GET
    @Operation(operationId = "getAllProjects",
               description = "returns all the projects from the database")
//    @APIResponse(responseCode = "401", )
    public Response getAllProjects(){ //TODO obsługa 401
        return Response.status(Response.Status.OK).entity(projectService.getAllProjects()).build();
    }

}
