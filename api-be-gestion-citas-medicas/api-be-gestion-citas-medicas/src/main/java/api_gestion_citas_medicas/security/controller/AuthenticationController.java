package api_gestion_citas_medicas.security.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api_gestion_citas_medicas.security.dto.LoginRequestDTO;
import api_gestion_citas_medicas.security.dto.LoginResponseDTO;
import api_gestion_citas_medicas.security.service.authentication.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthenticationService authenticationService;

	// Recomendable
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> signinHeader(HttpServletResponse response, @RequestBody LoginRequestDTO request) {
		
		log.info("login ...");
		
		String token=authenticationService.login(request).token();
		log.info("token {}",token);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Authorization", "Bearer " + token);

		return ResponseEntity.ok().headers(responseHeaders).build();
	}
	
	
	@PostMapping("/login-body")
	public ResponseEntity<Map<String, String>> signinBody(HttpServletResponse response, @RequestBody LoginRequestDTO request) {
		
		log.info("login ...");
		
		String token=authenticationService.login(request).token();
		log.info("token {}",token);

		Map<String, String> resp = new HashMap<>();
		resp.put("token", token);

		return ResponseEntity.ok().body(resp);
	}
}
