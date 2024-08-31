package pbs.ap.auth;

public interface AuthService {
    String authenticate(AuthRequest authRequest);
    boolean register(RegisterRequest registerRequest);
}
