package api_gestion_citas_medicas.security.service.authentication;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import api_gestion_citas_medicas.security.dto.LoginRequestDTO;
import api_gestion_citas_medicas.security.dto.LoginResponseDTO;
import api_gestion_citas_medicas.security.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

	private final UserDetailsService userDetailsService;
	
	private final JwtService jwtService;
	
	private final AuthenticationManager authenticationManager;
	
	@Override
	public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
		log.info("loginRequestDTO {}",loginRequestDTO);
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.userName(), loginRequestDTO.password()));
		
		UserDetails userDetails= userDetailsService.loadUserByUsername(loginRequestDTO.userName());
		
		log.info("userDetails {}",userDetails);
		
		if (isNull(userDetails)) {
			throw new IllegalArgumentException("Invalid user or password.");
		}

		String token = jwtService.generateToken(userDetails);
		
		return new LoginResponseDTO(token);
	}

}
