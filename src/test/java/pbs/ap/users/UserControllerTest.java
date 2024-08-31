package pbs.ap.users;

import io.quarkus.test.junit.QuarkusTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.AnyOf.anyOf;

@QuarkusTest
public class UserControllerTest {

    @Test
    public void testCreateUserSuccess() {
        User newUser = new User();
        newUser.email = "jan@test";
        newUser.name = "Jan";
        newUser.lastName = "Malinowski";
        newUser.setPassword("123");
        given()
                .contentType("application/json")
                .body(newUser)
                .when().post("/user")
                .then()
                .statusCode(201);
    }

    @Test
    public void testCreateUserFail() {
        User badUser = new User();

        given()
                .contentType("application/json")
                .body(badUser)
                .when().post("/user")
                .then()
                .statusCode(400);
    }
    @Test
    public void testCreateAndUpdateUser() {
        User newUser = new User();
        newUser.email = "jan@test";
        newUser.name = "Jan";
        newUser.lastName = "Malinowski";
        newUser.setPassword("123");
        given()
                .contentType("application/json")
                .body(newUser)
                .when().post("/user")
                .then()
                .statusCode(201);

        int testUserId = 1;
        User testUser = new User();
        testUser.name = "Jakub";
        testUser.lastName = "Kowalski";
        testUser.email = "jakub@test";
        given()
                .contentType("application/json")
                .pathParam("id", testUserId)
                .body(testUser)
                .when().put("/user/{id}")
                .then()
                .statusCode(200);
    }
    @Test
    public void testUpdateUserFail() {
        int badUserId = 5;
        User badUser = new User();
        given()
                .contentType("application/json")
                .pathParam("id", badUserId)
                .body(badUser)
                .when().put("/user/{id}")
                .then()
                .statusCode(400);
    }
    @Test
    public void testGetAllUsers() {
        given()
                .when().get("/user/all")
                .then()
                .statusCode(anyOf(is(200), is(404)))
                .contentType(anyOf(is("application/json"), Matchers.is("")));
    }

    @Test
    public void testGetUserById() {
        int testUserId = 1;

        given()
                .pathParam("id", testUserId)
                .when().get("/user/{id}")
                .then()
                .statusCode(200)
                .body("id", is(testUserId));
    }

    @Test
    public void testGetUserByIdNotFound() {
        long testUserId = 999;

        given()
                .pathParam("id", testUserId)
                .when().get("/user/{id}")
                .then()
                .statusCode(404);
    }
}
