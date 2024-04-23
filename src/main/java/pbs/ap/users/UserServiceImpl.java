package pbs.ap.users;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jboss.logging.Logger;
import pbs.ap.projects.Project;
import pbs.ap.projects.ProjectServiceImpl;

import java.util.List;
import java.util.Optional;
@ApplicationScoped
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
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
        User.persist(user);
        return user.isPersistent();
    }
    @Transactional
    @Override
    public boolean deleteUserById(long id){
        LOG.debug(">>>deleteUserById<<<");
        return User.deleteById(id);
    }
}
