package pbs.ap.chat;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import jakarta.websocket.Session;
import lombok.RequiredArgsConstructor;
import org.jboss.logging.Logger;

@ServerEndpoint("/chat/{username}")
@ApplicationScoped
@RolesAllowed({"ADMIN", "USER"})
//@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ChatSocket {

    Map<String, Session> sessions = new ConcurrentHashMap<>();
    private static final Logger LOG = Logger.getLogger(ChatSocket.class);
  /*  @Inject
    MessageService messageService;
    @Inject
    UserService userService;
    private User chatUser;*/


    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
   /*     Optional<User> user = userService.getUserByEmail(username);
        if(user.isPresent()) {
            chatUser = user.get();
            broadcast("User " + chatUser.name + " joined");
            sessions.put(username, session);
        }else{
            broadcast("Unknown user tried to join.");
        }*/
        sessions.put(username, session);
        broadcast("User " + username + " connected");

    }

    @OnClose
    public void onClose(Session session, @PathParam("username") String username) {
        sessions.remove(username);
        broadcast("User " + username + " left");
    }

    @OnError
    public void onError(Session session, @PathParam("username") String username, Throwable throwable) {
        sessions.remove(username);
        broadcast("User " + username + " left on error: " + throwable);
    }

    @OnMessage
    public void onMessage(String messageText, @PathParam("username") String username) {
      /*  broadcast(">> " + chatUser.name + ": " + messageText);
        if(chatUser != null) {
            Message message = new Message();
            message.text = messageText;
            message.creationTime = LocalDateTime.now();
            message.sender = chatUser;
            messageService.createMessage(message);
        }*/
        broadcast(">> " + username + ": " + messageText);
    }

    private void broadcast(String message) {
        sessions.values().forEach(s -> {
            s.getAsyncRemote().sendObject(message, result ->  {
                if (result.getException() != null) {
                    LOG.error(">>>broadcast<<< ERROR: ", result.getException());
                }
            });
        });
    }

}