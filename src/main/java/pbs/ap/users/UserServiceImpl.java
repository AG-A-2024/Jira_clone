package pbs.ap.users;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.security.credential.PasswordCredential;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.jboss.logging.Logger;
//import io.quarkus.elytron.security.common.BcryptUtil;


import java.util.List;
import java.util.Optional;
@ApplicationScoped
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

    @Override
    public List<User> getAllUsers() {
        LOG.debug(">>>getAllUsers<<<");
        return User.listAll();
    }

    @Override
    public Optional<User> getUserById(long id) {
        LOG.debug(">>>getUserById<<<");
        return User.findByIdOptional(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        LOG.debug(">>>getUserByEmail<<<");
        return User.find("email", email).firstResultOptional();
    }

    @Transactional
    @Override
    public boolean createUser(User user) {
        LOG.debug(">>>createUser<<<");
        user.password = BcryptUtil.bcryptHash(user.password);
        user.persistAndFlush();

        return user.isPersistent();
    }

    @Transactional
    @Override
    public boolean updateUser(long id, User user) {
        LOG.debug(">>>updateUser<<<");
        Optional<User> u = getUserById(id);

        if (u.isPresent()) {
            try {

                User oldUser = u.get();
                oldUser.email = user.email;
                oldUser.name = user.name;
                oldUser.lastName = user.lastName;
                return createUser(oldUser);
            } catch (Exception e) {
                LOG.error(">>>updateUser<<< ERROR: ", e);
            }
        }
        return false;
    }

    @Transactional
    @Override
    public boolean deleteUserById(long id){
        LOG.debug(">>>deleteUserById<<<");
        return User.deleteById(id);
    }
}