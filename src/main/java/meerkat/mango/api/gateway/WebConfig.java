package meerkat.mango.api.gateway;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")

            // Specify an origins
            .allowedOrigins("http://localhost:3001") // or specify the allowed origins
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .allowedHeaders("Origin", "Content-Type", "Accept")
            // .allowCredentials(true)
            .maxAge(3600); // max age in seconds
    }
}
