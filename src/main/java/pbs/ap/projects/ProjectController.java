package pbs.ap.projects;

import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import jakarta.ws.rs.QueryParam;
import java.util.Optional;


@ApplicationScoped
@Path("/projects")
@Produces({"application/json","application/problem+json"})
@RolesAllowed({"ADMIN", "USER"})
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final SecurityIdentity securityIdentity;

    @GET
    @RolesAllowed("ADMIN")
    @Operation(operationId = "getAllProjects",
               description = "Returns all the projects from the database")
    public Response getAllProjects(){
        return Response.status(Response.Status.OK).entity(projectService.getAllProjects()).build();
    }

    @GET
    @Path("/user/")
    @Operation(operationId = "getUserProjects",
                description = "Returns all projects connected to the logged in user")
    @APIResponses(value = {
            @APIResponse(responseCode = "401", description = "Unauthorized; you need to be logged in")
    })
    public Response getUserProjects(){
        String username = securityIdentity.getPrincipal().getName();
        return Response.status(Response.Status.OK).entity(projectService.getAllUserProjects(username)).build();
    }

    @GET
    @Path("/{Id}/")
    @RolesAllowed("ADMIN")
    @Operation(operationId = "getProjectById",
                description = "Returns a single project with a provided id")
    @APIResponses(value = {
        @APIResponse(responseCode = "404", description = "Project not found")
    })
    public Response getProjectById(@PathParam("Id") Long id){
        Optional<Project> project = projectService.getProjectById(id);
        Response response;
        if (project.isPresent()) {
            response = Response.status(Response.Status.OK).entity(project.get()).build();
        } else {
            response = Response.status(Response.Status.NOT_FOUND).entity("Project with a given id doesn't exist").build();
        }
        return response;
    }

    @GET
    @Operation(operationId = "getProjectByName",
            description = "Returns a project by a given name")
    @Path("/byName/{name}")
    public Response getProjectByName(@PathParam("name") String name){
        Optional<Project> project = projectService.getProjectByProjectName(name);
        Response response;
        if (project.isPresent()) {
            response = Response.status(Response.Status.OK).entity(project.get()).build();
        } else {
            response = Response.status(Response.Status.NOT_FOUND).entity("Project with a given name doesn't exist").build();
        }
        return response;
    }

    @POST
    @Operation(operationId = "addProject",
               description = "Returns an added project or an error if project can't be persisted")
    public Response addProject(@QueryParam("name") String name, @QueryParam("description") String description){
        Project project = new Project(name, description);
        boolean isAdded = projectService.addProject(project);
        Response response;
        if (isAdded) {
            response = Response.status(Response.Status.CREATED).entity(String.format("Project %s has been added", name)).build();
        } else {
            response = Response.status(Response.Status.BAD_REQUEST).entity("Project couldn't be created; check parameters").build();
        }
        return response;
    }

    @DELETE
    @Operation(operationId = "deleteProjectById",
                description = "Returns the result of a project deletion; project is provided by its id")
    @Path("/{Id}")
    public Response deleteProjectById(@PathParam("Id") Long id){
        Response response;
        boolean isDeleted = projectService.deleteProjectById(id);
        if (isDeleted){
            response = Response.status(Response.Status.OK).entity(String.format("Project with id %s has been deleted", id)).build();
        } else {
            response = Response.status(Response.Status.NOT_FOUND).entity(String.format("Project with id %s didn't exist", id)).build();
        }
        return response;
    }

    @PATCH
    @Operation(operationId = "updateProjectById",
            description = "Returns the result of a project update; project is provided by its id")
    @Path("/{id}")
    public Response updateProjectById(@PathParam("id") Long id, Project project){
        Project dbProject = projectService.updateProjectById(id, project);
        if (dbProject == null) {
            throw new NotFoundException();
        }
        return Response.status(Response.Status.OK).entity("Project has been updated" + dbProject.toString()).build();
    }

}
