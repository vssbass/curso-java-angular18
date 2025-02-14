package api_gestion_citas_medicas.business.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalConfig {
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
