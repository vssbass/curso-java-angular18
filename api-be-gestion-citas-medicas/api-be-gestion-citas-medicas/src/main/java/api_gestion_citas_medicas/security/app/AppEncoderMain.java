package api_gestion_citas_medicas.security.app;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AppEncoderMain {
	public static void main(String[] args) {
		
		System.out.println(new BCryptPasswordEncoder().encode("123456"));
	}
}
