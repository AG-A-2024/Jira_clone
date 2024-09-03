package pbs.ap.users;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
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
    @TestSecurity(user = "admin@test.pl", roles = "ADMIN")
    public void testCreateUserSuccess() {
        User newUser = new User();
        newUser.email = "jan@test";
        newUser.name = "Jan";
        newUser.lastName = "Malinowski";
        newUser.setPassword("123");
        given()
                .contentType("application/json")
                .body(newUser)
                .when().post("/users")
                .then()
                .statusCode(201);
    }

    @Test
    @TestSecurity(user = "admin@test.pl", roles = "ADMIN")
    public void testCreateUserFail() {
        User badUser = new User();

        given()
                .contentType("application/json")
                .body(badUser)
                .when().post("/users")
                .then()
                .statusCode(400);
    }
    @Test
    @TestSecurity(user = "admin@test.pl", roles = "ADMIN")
    public void testCreateAndUpdateUser() {
        User newUser = new User();
        newUser.email = "jan@test2";
        newUser.name = "Jan";
        newUser.lastName = "Malinowski";
        newUser.setPassword("123");
        given()
                .contentType("application/json")
                .body(newUser)
                .when().post("/users")
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
                .when().put("/users/{id}")
                .then()
                .statusCode(200);
    }
    @Test
    @TestSecurity(user = "admin@test.pl", roles = "ADMIN")
    public void testUpdateUserFail() {
        int badUserId = 9999;
        User badUser = new User();
        given()
                .contentType("application/json")
                .pathParam("id", badUserId)
                .body(badUser)
                .when().put("/users/{id}")
                .then()
                .statusCode(400);
    }
    @Test
    @TestSecurity(user = "admin@test.pl", roles = "ADMIN")
    public void testGetAllUsers() {
        given()
                .when().get("/users/all")
                .then()
                .statusCode(anyOf(is(200), is(404)))
                .contentType(anyOf(is("application/json"), Matchers.is("")));
    }

    @Test
    @TestSecurity(user = "admin@test.pl", roles = "ADMIN")
    public void testGetUserById() {
        int testUserId = 1;

        given()
                .pathParam("id", testUserId)
                .when().get("/users/{id}")
                .then()
                .statusCode(200)
                .body("id", is(testUserId));
    }

    @Test
    @TestSecurity(user = "admin@test.pl", roles = "ADMIN")
    public void testGetUserByIdNotFound() {
        long testUserId = 999;

        given()
                .pathParam("id", testUserId)
                .when().get("/users/{id}")
                .then()
                .statusCode(404);
    }
}
