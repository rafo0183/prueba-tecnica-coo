package cl.coopeuch.pruebatecnica.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class ConfApi {
	@Value("${apitext.title}")
	String title;
	
	@Value("${apitext.version}")
	String version;
	
	@Value("${apitext.description}")
	String description;
	
	
	@Bean
	OpenAPI customOpenApi(){
		return new OpenAPI().info(new Info()
				.title(title)
				.version(version)
				.description(description)
		);
	}
}
