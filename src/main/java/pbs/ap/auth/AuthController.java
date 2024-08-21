package pbs.ap.auth;

import io.quarkus.security.AuthenticationException;
import io.quarkus.security.AuthenticationFailedException;
import jakarta.annotation.security.PermitAll;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@Path("/auth")
@PermitAll
@RequiredArgsConstructor
@ApplicationScoped
public class AuthController {
    private final AuthService authService;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(AuthRequest request) {
        try {
            String token = authService.authenticate(request);
            return Response.ok(token).build(); // HTTP 200 with the token
        } catch (AuthenticationFailedException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid credentials").build(); // HTTP 401
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build(); // HTTP 500
        }
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(RegisterRequest registerRequest) {
        try {
            boolean isRegistered = authService.register(registerRequest);
            if (isRegistered) {
                return Response.status(Response.Status.CREATED).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Registration failed").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build();
        }
    }
}
