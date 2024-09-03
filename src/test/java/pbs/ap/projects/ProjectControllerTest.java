package pbs.ap.projects;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Inject;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pbs.ap.projects.Project;
import pbs.ap.users.User;
import pbs.ap.users.UserService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.AnyOf.anyOf;

@QuarkusTest
public class ProjectControllerTest {
   /* private String generateToken(String username, Set<String> roles) {
        return Jwt.issuer("https://jira_clone.com/issuer")
                .upn(username)
                .groups(roles)
                .expiresIn(Duration.ofHours(5L))
                .sign();
    }*/
   @Inject
   UserService userService;

    User existingUser;

    @BeforeEach
    public void setup() {
        existingUser = userService.getUserById(1L).orElseThrow(() -> new RuntimeException("User not found"));
    }
    @Test
    @TestSecurity(user = "admin@test.pl", roles = "ADMIN")
    public void testCreateProjectSuccess() {
        Project newProject = new Project();
        newProject.projectDescription = "Test description";
        newProject.projectName = "Test project";
        newProject.creationTime = LocalDateTime.now();
        newProject.releaseDate = LocalDateTime.now().plusDays(7);

    /*    String projectJson = """
                {
                  "projectName": "Test project",
                  "projectDescription": "Test description",
                  "creationTime": "2024-09-02T12:00:00",
                  "releaseDate": "2024-12-15T00:00:00"
                }""";*/
        given()
                .contentType("application/json")
                .queryParam("ownerId", 1)
                .body(newProject)
                .when().post("/projects")
                .then()
                .statusCode(201);
    }
     @Test
     @TestSecurity(user = "admin@test.pl", roles = "ADMIN")
     public void testCreateProjectFail() {
         Project badProject = new Project();

        given()
                .contentType("application/json")
                .body(badProject)
                .when().post("/projects")
                .then()
                .statusCode(400);
    }
    @Test
    @TestSecurity(user = "admin@test.pl", roles = "ADMIN")
    public void testCreateAndUpdateProject() {
        Project newProject = new Project();
        newProject.projectDescription = "Test description";
        newProject.projectName = "Test project";
        newProject.creationTime = LocalDateTime.now();
        newProject.releaseDate = LocalDateTime.now().plusDays(7);
        given()
                .contentType("application/json")
                .queryParam("ownerId", 1)
                .body(newProject)
                .when().post("/projects")
                .then()
                .statusCode(201);

        int testProjectId = 6;
        Project testProject = new Project();
        testProject.projectDescription = "Updated description";
        testProject.projectName = "Updated project";
        testProject.creationTime = LocalDateTime.now();
        testProject.releaseDate = LocalDateTime.now().plusDays(14);
        given()
                .contentType("application/json")
                .pathParam("id", testProjectId)
                .body(testProject)
                .when().patch("/projects/{id}")
                .then()
                .statusCode(200);
    }
    @Test
    @TestSecurity(user = "admin@test.pl", roles = "ADMIN")
    public void testUpdateProjectFail() {
        int badProjectId = 999;
        Project badProject = new Project();
        given()
                .contentType("application/json")
                .pathParam("id", badProjectId)
                .body(badProject)
                .when().patch("/projects/{id}")
                .then()
                .statusCode(404);
    }
    @Test
    @TestSecurity(user = "admin@test.pl", roles = "ADMIN")
    public void testGetAllProjects() {
        given()
                .when().get("/projects")
                .then()
                .statusCode(anyOf(is(200), is(404)))
                .contentType(anyOf(is("application/json"), Matchers.is("")));
    }

    @Test
    @TestSecurity(user = "admin@test.pl", roles = "ADMIN")
    public void testGetProjectById() {
        int testProjectId = 1;

        given()
                .pathParam("id", testProjectId)
                .when().get("/projects/{id}")
                .then()
                .statusCode(200)
                .body("id", is(testProjectId));
    }

    @Test
    @TestSecurity(user = "admin@test.pl", roles = "ADMIN")
    public void testGetProjectByIdNotFound() {
        long testProjectId = 999;
        given()
                .pathParam("id", testProjectId)
                .when().get("/projects/{id}")
                .then()
                .statusCode(404);
    }
}
