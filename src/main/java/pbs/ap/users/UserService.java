package pbs.ap.users;
import io.quarkus.elytron.security.common.BcryptUtil;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(long id);
    Optional<User> getUserByEmail(String email);
    boolean createUser(User user);
    boolean updateUser(long id, User user);
    boolean deleteUserById(long id);

    static boolean matches(User user, String password){
        return BcryptUtil.matches(password, user.password);
    }
}
