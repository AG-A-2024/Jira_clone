package pbs.ap.messages;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import jakarta.inject.Inject;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pbs.ap.users.User;
import pbs.ap.users.UserService;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.AnyOf.anyOf;

@QuarkusTest
public class MessageControllerTest {
    @Inject
    UserService userService;
   User existingUser;

    @BeforeEach
    public void setup() {
        existingUser = userService.getUserById(1L).orElseThrow(() -> new RuntimeException("User not found"));
      //  System.out.println(existingUser.email);
    }
    @Test
    @TestSecurity(user = "admin@test.pl", roles = "ADMIN")
    public void testCreateMessageSuccess() {

        String messageJson = """
{
    "text": "Hello, World!",
    "sender": {
        "id": 1
    }
}""";
        given()
                .contentType("application/json")
                .body(messageJson)
                .when().post("/messages")
                .then()
                .statusCode(201);
    }
    @Test
    @TestSecurity(user = "admin@test.pl", roles = "ADMIN")
    public void testCreateMessageFail() {
        Message badMessage = new Message();

        given()
                .contentType("application/json")
                .body(badMessage)
                .when().post("/messages")
                .then()
                .statusCode(400);
    }


    @Test
    @TestSecurity(user = "admin@test.pl", roles = "ADMIN")
    public void testGetAllMessages() {
        given()
                .when().get("/messages/all")
                .then()
                .statusCode(anyOf(is(200), is(404)))
                .contentType(anyOf(is("application/json"), Matchers.is("")));
    }

    @Test
    @TestSecurity(user = "admin@test.pl", roles = "ADMIN")
    public void testGetMessageById() {
        int testMessageId = 1;

        given()
                .pathParam("id", testMessageId)
                .when().get("/messages/{id}")
                .then()
                .statusCode(200)
                .body("id", is(testMessageId));
    }

    @Test
    @TestSecurity(user = "admin@test.pl", roles = "ADMIN")
    public void testGetMessageByIdNotFound() {
        long testMessageId = 999;
        given()
                .pathParam("id", testMessageId)
                .when().get("/messages/{id}")
                .then()
                .statusCode(404);
    }
}
