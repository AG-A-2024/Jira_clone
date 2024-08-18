package pbs.ap.auth;

public record RegisterRequest(String username, String firstName, String lastName, String index, boolean isDaily, String password) {
}
