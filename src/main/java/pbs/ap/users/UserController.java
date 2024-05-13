package pbs.ap.users;

import io.smallrye.jwt.build.JwtEncryption;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.logging.Logger;
import org.jboss.logging.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;
import pbs.ap.projects.ProjectService;

import java.util.Optional;

@ApplicationScoped
@Path("/user")
@Produces({"application/json","application/problem+json"})
//@RolesAllowed({"ADMIN", "USER"})
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private static final Logger LOG = Logger.getLogger(UserController.class);

    @GET
    @Operation(operationId = "getAllUsers",
            description = "returns all the users from the database")
    @Path("/all")
  //  @RolesAllowed("ADMIN")
    public Response getAllUsers(){
        return !userService.getAllUsers().isEmpty() ?
                Response.status(Response.Status.OK).entity(userService.getAllUsers()).build() :
                Response.status(Response.Status.NOT_FOUND).build();
    }
    @GET
    @Path("/{id}")
    @Operation(operationId = "getUserById",
            description = "returns user by id")
    public Response getUserById(@PathParam("id") long id){
        Optional<User> user = userService.getUserById(id);
        return  user.isPresent() ?
                Response.status(Response.Status.OK).entity(user).build() :
                Response.status(Response.Status.NOT_FOUND).build();
    }
    @GET
    @Operation(operationId = "getUserByEmail",
            description = "returns user by email")
    public Response getUserByEmail(@QueryParam("email")String email){
        Optional<User> user = userService.getUserByEmail(email);
        return user.isPresent() ?
                Response.status(Response.Status.OK).entity(user).build() :
                Response.status(Response.Status.NOT_FOUND).build();
    }
    @POST
    @Operation(operationId = "createUser",
            description = "creates user")
    public Response createUser(@RequestBody User user){
        try{
            userService.createUser(user);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e){
            LOG.error(">>>createUser<<< ERROR: ", e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    @PUT
    @Path("/{id}")
    @Operation(operationId = "updateUser",
            description = "updates user")
    public Response updateUser(@PathParam("id") long id, @RequestBody User userWithNewData){
         return userService.updateUser(id, userWithNewData) ?
         Response.status(Response.Status.CREATED).build() :
         Response.status(Response.Status.BAD_REQUEST).build();


   //     return Response.status(Response.Status.NOT_IMPLEMENTED).build();
    }
    @DELETE
    @Path("/{id}")
    @Operation(operationId = "deleteUser",
            description = "deletes user")
    public Response deleteUser(@PathParam("id") long id){
        return userService.deleteUserById(id) ?
                Response.status(Response.Status.OK).build() :
                Response.status(Response.Status.NOT_FOUND).build();
    }
}
