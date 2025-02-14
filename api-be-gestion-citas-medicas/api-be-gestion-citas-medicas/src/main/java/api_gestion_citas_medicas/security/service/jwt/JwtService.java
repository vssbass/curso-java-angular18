package api_gestion_citas_medicas.security.service.jwt;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
	String extractUser(String token);

	String generateToken(UserDetails userDetails);

	boolean isTokenValid(String token, UserDetails userDetails);
}
