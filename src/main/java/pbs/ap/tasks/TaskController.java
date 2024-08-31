package pbs.ap.tasks;

import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;

import static io.quarkus.arc.ComponentsProvider.LOG;

@ApplicationScoped
@Path("/tasks")
@Produces({"application/json","application/problem+json"})
@RolesAllowed({"ADMIN", "USER"})
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final TaskServiceImpl taskServiceImpl;

    @GET
    @RolesAllowed("ADMIN")
    @Operation(operationId = "getAllTasks",
            description = "returns all the tasks from the database")
//    @APIResponse(responseCode = "401", )
    public Response getAllTasks(){
        return Response.status(Response.Status.OK).entity(taskService.getAllTasks()).build();
    }

    @GET
    @RolesAllowed("ADMIN")
    @Path("/{id}")
    @Operation(operationId = "getTaskById",
            description = "returns one task from database")
    public Response getTaskById(@PathParam("id") Long id){

        if(id == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        else{
            return Response.status(Response.Status.OK).entity(taskService.getTaskById(id)).build();
        }
    }

    @POST
    public Response addTask(Task task) {
        boolean isTaskAdded = taskService.addTask(task);
        if (isTaskAdded) {
            return Response.status(Response.Status.CREATED).entity(task).build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("Task couldn't be created. Task content: " + task)
                .build();
    }

    @PUT
    @Path("/{id}")
    @Operation(operationId = "updateTask",
            description = "updates an existing task")
    public Response updateTask(@PathParam("id") Long id, Task task) {
        if (task == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Task updatedTask = taskService.update(task);
        return Response.status(Response.Status.OK).entity(updatedTask).build();
    }


    @DELETE
    @Path("/{id}")
    @Operation(operationId = "deleteTaskById",
            description = "deletes a task by its ID")
    public Response deleteTaskById(@PathParam("id") Long id) {
        Task deletedTask = taskService.deleteTaskById(id);
        if (deletedTask == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(deletedTask).build();
    }


}
