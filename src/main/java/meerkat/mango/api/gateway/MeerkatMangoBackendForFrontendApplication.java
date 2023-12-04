package meerkat.mango.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MeerkatMangoBackendForFrontendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeerkatMangoBackendForFrontendApplication.class, args);
	}

}
