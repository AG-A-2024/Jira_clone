package pbs.ap.auth;

import io.quarkus.security.AuthenticationFailedException;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.CreationException;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import pbs.ap.users.Roles;
import pbs.ap.users.User;
import pbs.ap.users.UserService;

import java.time.Duration;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class AuthServiceImpl implements AuthService {

    private static final Logger LOG = Logger.getLogger(AuthServiceImpl.class);
    private final String issuer;
    private final UserService userService;

    public AuthServiceImpl(@ConfigProperty(name = "jwt.issuer") String issuer, UserService userService) {
        this.issuer = issuer;
        this.userService = userService;
    }

    @Override
    public String authenticate(AuthRequest authRequest) {
        LOG.debug(">>>authenticate; Mail: " + authRequest.username());
        Optional<User> user = User.find("email", authRequest.username()).firstResultOptional();
        if (user.isEmpty() || UserService.matches(user.get(), authRequest.password())) {
            throw new AuthenticationFailedException("Invalid credentials");
        }
        Set<String> roles = user.get().roles.stream().map(Enum::name).collect(Collectors.toUnmodifiableSet());
        return Jwt.issuer(issuer)
                .upn(authRequest.username())
                .groups(roles)
                .expiresIn(Duration.ofHours(5L))
                .sign(); //TODO osobna tabela z kluczami do podpisywania; klucz per user
    }

    @Override
    public boolean register(RegisterRequest registerRequest) {
        LOG.debug(">>>register; Mail: " + registerRequest.username());
        Optional<User> user = User.find("email", registerRequest.username()).firstResultOptional();
        if (user.isPresent()) {
            throw new CreationException("User " + registerRequest.username() + " already exists");
        }
        User newUser = new User();
        newUser.email = registerRequest.username();
        newUser.indexNr = registerRequest.index();
        newUser.name = registerRequest.firstName();
        newUser.lastName = registerRequest.lastName();
        newUser.setPassword(registerRequest.password());
        newUser.roles = Set.of(Roles.USER);
        return userService.createUser(newUser);
    }
}
