package pbs.ap.messages;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jboss.logging.Logger;
import pbs.ap.users.User;
import pbs.ap.users.UserServiceImpl;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private static final Logger LOG = Logger.getLogger(MessageServiceImpl.class);

    @Override
    public List<Message> getAllMessages() {
        LOG.debug(">>>getAllMessages<<<");

        return Message.listAll();
    }

    @Override
    public Optional<Message> getMessageById(long id) {
        LOG.debug(">>>getMessageById<<<");

        return Message.findByIdOptional(id);
    }

    @Override
    public List<Message> getAllUserMessages(long userId) {
        LOG.debug(">>>getAllUserMessages<<<");
        return Message.find("sender.id", userId).list();
    }
    @Transactional
    @Override
    public boolean createMessage(Message message) {
        LOG.debug(">>>createMessage<<<");

        message.persistAndFlush();
        return message.isPersistent();
    }
    @Transactional
    @Override
    public boolean deleteMessageById(long id) {
        LOG.debug(">>>deleteMessageById<<<");
        return Message.deleteById(id);    }
}
