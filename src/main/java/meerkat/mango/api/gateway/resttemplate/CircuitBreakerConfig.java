package meerkat.mango.api.gateway.resttemplate;

import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CircuitBreakerConfig {

    @Bean
    public CircuitBreakerConfigCustomizer searchCircuitBreakerConfig() {
        return CircuitBreakerConfigCustomizer.of("search",
                builder -> builder
                        .slidingWindowSize(10)
                        .slidingWindowType(io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                        .waitDurationInOpenState(Duration.ofSeconds(5))
                        .minimumNumberOfCalls(5)
                        .build());
    }

}
