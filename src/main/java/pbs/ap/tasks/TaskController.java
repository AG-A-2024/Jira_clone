package pbs.ap.tasks;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.Operation;

import java.util.Optional;
//taski dzialaja
@ApplicationScoped
@Path("/tasks")
@Produces({"application/json","application/problem+json"})
@RolesAllowed({"ADMIN", "USER"})
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final TaskServiceImpl taskServiceImpl;

    @GET
    @Operation(operationId = "getAllTasks",
            description = "returns all the tasks from the database")
//    @APIResponse(responseCode = "401", )
    public Response getAllTasks(){ //TODO obsługa 401
        return Response.status(Response.Status.OK).entity(taskService.getAllTasks()).build();
    }

    @GET
    @Path("/{id}")
    @Operation(operationId = "getTaskById",
            description = "returns one task from database")
    public Response getTaskById(@PathParam("id") Long id) {
        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            Optional<Task> taskOpt = taskService.findTaskById(id);
            if (taskOpt.isPresent()) {
                return Response.status(Response.Status.OK).entity(taskOpt.get()).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }
    }

    @GET
    @Path("/taskname/{taskName}")
    @Operation(operationId = "getTaskByTaskName",
            description = "returns a task by its name")
    public Response getTaskByTaskName(@PathParam("taskName") String taskName) {
        Task task = taskService.getTaskByTaskName(taskName);
        if (task == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(task).build();
    }


    @POST
    public Response addTask(Task task) {
        try {
            Task addedTask = taskService.addTask(task);
            return Response.status(Response.Status.CREATED)
                    .entity(addedTask)
                    .build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        } catch (Exception e) {
            // Obsługa innych błędów
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal server error")
                    .build();
        }
    }


    @PUT
    @Path("/{id}")
    @Operation(operationId = "updateTask",
            description = "updates an existing task")
    public Response updateTask(@PathParam("id") Long id, Task taskToUpdate) {
        if (taskToUpdate == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        try {
            Task updatedTask = taskService.update(id, taskToUpdate);
            return Response.status(Response.Status.OK).entity(updatedTask).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
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
