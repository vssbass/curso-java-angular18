package api_gestion_citas_medicas.security.service.authentication;

import api_gestion_citas_medicas.security.dto.LoginRequestDTO;
import api_gestion_citas_medicas.security.dto.LoginResponseDTO;

public interface AuthenticationService {
	
	LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}
