package pbs.ap.messages;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.logging.Logger;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Path("/messages")
@Produces({"application/json","application/problem+json"})
@RolesAllowed({"ADMIN", "USER"})
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private static final Logger LOG = Logger.getLogger(MessageController.class);

    @GET
    @Operation(operationId = "getAllMessages",
            description = "Returns all the messages from the database")
    @Path("/all")
    @RolesAllowed("ADMIN")
    public Response getAllMessages(){
        return !messageService.getAllMessages().isEmpty() ?
                Response.status(Response.Status.OK).entity(messageService.getAllMessages()).build() :
                Response.status(Response.Status.NOT_FOUND).build();
    }
    @GET
    @Path("/{id}")
    @Operation(operationId = "getMessageById",
            description = "Returns message by id")
    public Response getMessageById(@PathParam("id") long id){
        Optional<Message> message = messageService.getMessageById(id);
        return  message.isPresent() ?
                Response.status(Response.Status.OK).entity(message).build() :
                Response.status(Response.Status.NOT_FOUND).build();
    }
    @GET
    @Operation(operationId = "getUserMessages",
            description = "Returns messages by user")
    public Response getUserMessages(@QueryParam("userId")long userId){
        List<Message> messages = messageService.getAllUserMessages(userId);
        return !messages.isEmpty() ?
                Response.status(Response.Status.OK).entity(messages).build() :
                Response.status(Response.Status.NOT_FOUND).build();
    }
    @POST
    @Operation(operationId = "createMessage",
            description = "Creates message")
    public Response createMessage(@RequestBody Message message){
        try{
            messageService.createMessage(message);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e){
            LOG.error(">>>createMessage<<< ERROR: ", e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    @DELETE
    @Path("/{id}")
    @Operation(operationId = "deleteMessage",
            description = "Deletes message")
    @RolesAllowed("ADMIN")
    public Response deleteMessage(@PathParam("id") long id){
        return messageService.deleteMessageById(id) ?
                Response.status(Response.Status.OK).build() :
                Response.status(Response.Status.NOT_FOUND).build();
    }
}
