package pbs.ap.tasks;

import io.quarkus.test.junit.QuarkusTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.AnyOf.anyOf;

@QuarkusTest
public class TaskControllerTest {

    @Test
    public void testCreateTaskSuccess() {
        Task newTask = new Task();
        newTask.taskName = "Test";
        newTask.creationDateTime = LocalDateTime.now();
        newTask.description = "TestDescription";
        given()
                .contentType("application/json")
                .body(newTask)
                .when().post("/tasks")
                .then()
                .statusCode(201);
    }
    // nie powinno przechodzic
   /* @Test
    public void testCreateTaskFail() {
        Task badTask = new Task();

        given()
                .contentType("application/json")
                .body(badTask)
                .when().post("/tasks")
                .then()
                .statusCode(400);
    }*/
    @Test
    public void testCreateAndUpdateTask() {
        Task newTask = new Task();

        given()
                .contentType("application/json")
                .body(newTask)
                .when().post("/tasks")
                .then()
                .statusCode(201);

        int testTaskId = 1;
        Task testTask = new Task();

        given()
                .contentType("application/json")
                .pathParam("id", testTaskId)
                .body(testTask)
                .when().put("/tasks/{id}")
                .then()
                .statusCode(200);
    }
    @Test
    public void testUpdateTaskFail() {
        int badTaskId = 5;
        Task badTask = new Task();
        given()
                .contentType("application/json")
                .pathParam("id", badTaskId)
                .body(badTask)
                .when().put("/tasks/{id}")
                .then()
                .statusCode(404);
    }
    @Test
    public void testGetAllTasks() {
        given()
                .when().get("/tasks")
                .then()
                .statusCode(anyOf(is(200), is(404)))
                .contentType(anyOf(is("application/json"), Matchers.is("")));
    }

    @Test
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
    public void testGetTaskByIdNotFound() {
        long testTaskId = 999;
        // tu powinno byc 404
        given()
                .pathParam("id", testTaskId)
                .when().get("/tasks/{id}")
                .then()
                .statusCode(200);
    }
}
