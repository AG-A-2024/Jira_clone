package pbs.ap.messages;

import pbs.ap.messages.Message;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    List<Message> getAllMessages();
    Optional<Message> getMessageById(long id);
    List<Message> getAllUserMessages(long userId);
    boolean createMessage(Message message);
    boolean deleteMessageById(long id);
}
