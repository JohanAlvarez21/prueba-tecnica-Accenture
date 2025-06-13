package com.app.shopping;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

@SpringBootApplication
public class ShoppingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingApplication.class, args);
	}

//	@Configuration
//	public class WebFluxCorsConfig implements WebFluxConfigurer {
//
//		@Override
//		public void addCorsMappings(CorsRegistry registry) {
//			registry.addMapping("/**")
//					.allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
//					.allowedOrigins("*");  // Aquí puedes restringir si lo deseas
//		}
//	}

//	@Bean
//	public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory){
//		ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
//		initializer.setConnectionFactory(connectionFactory);
//		initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
//		return initializer;
//	}

//	@Bean
//	public ApplicationRunner initializer(ConnectionFactory connectionFactory){
//
//		return args -> {
//			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("schema.sql");
//			String schema = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
//			DatabaseClient client = DatabaseClient.create(connectionFactory);
//
//			Arrays.stream(schema.split(";"))
//					.map(String::trim)
//					.filter(s -> !s.isBlank())
//					.forEach(sql ->
//						client.sql(sql).then().subscribe());
//		};
//	}

}
