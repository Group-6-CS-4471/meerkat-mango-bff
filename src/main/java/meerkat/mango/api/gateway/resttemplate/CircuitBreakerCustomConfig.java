package meerkat.mango.api.gateway.resttemplate;

import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.HttpServerErrorException;

import java.time.Duration;

import static io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType.TIME_BASED;

@Configuration
public class CircuitBreakerCustomConfig {

    @Bean
    public CircuitBreakerConfigCustomizer searchCircuitBreakerConfig() {
        return CircuitBreakerConfigCustomizer.of("search",
                builder -> builder
                        .slidingWindowSize(10)
                        .slidingWindowType(TIME_BASED)
                        .waitDurationInOpenState(Duration.ofSeconds(5))
                        .minimumNumberOfCalls(1)
                        .recordExceptions(HttpServerErrorException.class)
                        .failureRateThreshold(1.0f)
                        .build());
    }
}
