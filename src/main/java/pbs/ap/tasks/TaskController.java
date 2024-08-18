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
    public Response getTaskById(@PathParam("id") Long id){

        if(id == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        else{
            return Response.status(Response.Status.OK).entity(taskService.getTaskById(id)).build();
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




  /*  @POST
    public Uni<Task> addTask(Task task) {
        return taskService.addTask(task)
                .onItem().invoke(() -> LOG.info("Task added: " + task.id))
                .onFailure().invoke(throwable -> LOG.error("Failed to add task: " + throwable.getMessage()));
    }*/

    @POST
    public Response addTask(Task task) {
        Task addedTask = taskService.addTask(task)
                .await().indefinitely();
        return Response.status(Response.Status.CREATED)
                .entity(addedTask)
                .build();
    }

    @PUT
    @Path("/{id}")
    @Operation(operationId = "updateTask",
            description = "updates an existing task")
    public Response updateTask(@PathParam("id") Long id, Task taskToUpdate) {
        if (taskToUpdate == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Task existingTask = taskService.getTaskById(id);
        if (existingTask == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingTask.setTaskName(taskToUpdate.getTaskName());
        existingTask.setSequence(taskToUpdate.getSequence());
        existingTask.setDescription(taskToUpdate.getDescription());
        existingTask.setDeliveryTime(taskToUpdate.getDeliveryTime());

        Task updatedTask = taskService.update(existingTask);
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
