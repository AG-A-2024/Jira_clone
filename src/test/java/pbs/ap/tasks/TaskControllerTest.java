package pbs.ap.tasks;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import pbs.ap.projects.Project;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.AnyOf.anyOf;

@QuarkusTest
public class TaskControllerTest {

    @Test
    @TestSecurity(user = "admin@test.pl", roles = "ADMIN")
    public void testCreateTaskSuccess() {
        Task newTask = new Task();
        newTask.taskName = "Test";
        newTask.creationDateTime = LocalDateTime.now();
        newTask.description = "TestDescription";
        Project p = new Project();
        p.id = 1L;
        newTask.project = p;
        given()
                .contentType("application/json")
                .body(newTask)
                .when().post("/tasks")
                .then()
                .statusCode(201);
    }
    @Test
    @TestSecurity(user = "admin@test.pl", roles = "ADMIN")
    public void testCreateTaskFail() {
        Task badTask = new Task();

        given()
                .contentType("application/json")
                .body(badTask)
                .when().post("/tasks")
                .then()
                .statusCode(400);
    }
    @Test
    @TestSecurity(user = "admin@test.pl", roles = "ADMIN")
    public void testCreateAndUpdateTask() {
        Task newTask = new Task();
        newTask.taskName = "Test";
        newTask.creationDateTime = LocalDateTime.now();
        newTask.description = "TestDescription";
        Project p = new Project();
        p.id = 1L;
        newTask.project = p;
        given()
                .contentType("application/json")
                .body(newTask)
                .when().post("/tasks")
                .then()
                .statusCode(201);

        int testTaskId = 1;
        Task testTask = new Task();
        testTask.taskName = "Updated Test";
        testTask.description = "Updated description";

        given()
                .contentType("application/json")
                .pathParam("id", testTaskId)
                .body(testTask)
                .when().put("/tasks/{id}")
                .then()
                .statusCode(200);
    }
    @Test
    @TestSecurity(user = "admin@test.pl", roles = "ADMIN")
    public void testUpdateTaskFail() {
        int badTaskId = 5;
        Task badTask = new Task();
        given()
                .contentType("application/json")
                .pathParam("id", badTaskId)
                .body(badTask)
                .when().put("/tasks/{id}")
                .then()
                .statusCode(400);
    }
    @Test
    @TestSecurity(user = "admin@test.pl", roles = "ADMIN")
    public void testGetAllTasks() {
        given()
                .when().get("/tasks")
                .then()
                .statusCode(anyOf(is(200), is(404)))
                .contentType(anyOf(is("application/json"), Matchers.is("")));
    }

    @Test
    @TestSecurity(user = "admin@test.pl", roles = "ADMIN")
    public void testGetTaskById() {
        int testTaskId = 1;

        given()
                .pathParam("id", testTaskId)
                .when().get("/tasks/{id}")
                .then()
                .statusCode(200)
                .body("id", is(testTaskId));
    }

    @Test
    @TestSecurity(user = "admin@test.pl", roles = "ADMIN")
    public void testGetTaskByIdNotFound() {
        long testTaskId = 999;
        given()
                .pathParam("id", testTaskId)
                .when().get("/tasks/{id}")
                .then()
                .statusCode(200);
    }
}
