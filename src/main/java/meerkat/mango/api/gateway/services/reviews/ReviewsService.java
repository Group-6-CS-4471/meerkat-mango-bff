package meerkat.mango.api.gateway.services.reviews;

import meerkat.mango.api.gateway.resttemplate.CustomClientHttpRequestFactory;
import meerkat.mango.api.gateway.services.Discovery;
import meerkat.mango.api.gateway.services.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
public class ReviewsService {

    private static final String REVIEWS_PATH = "reviews";
    private final RestTemplate restTemplate;
    private final Discovery discovery;

    @Autowired
    public ReviewsService(final Discovery discovery) {
        this.discovery = discovery;
        this.restTemplate = new RestTemplateBuilder()
                .setConnectTimeout(Duration.of(2, ChronoUnit.SECONDS))
                .setReadTimeout(Duration.of(2, ChronoUnit.SECONDS))
                .requestFactory(CustomClientHttpRequestFactory.class)
                .build();
    }

    public ReviewResponse getReviews(final String productId, final String provider) {
        final var url = discovery.getService(ServiceType.REVIEWS);
        if (url == null) {
            return null;
        }

        final var properUrl = UriComponentsBuilder.fromHttpUrl(url).pathSegment(REVIEWS_PATH, productId, provider).build();
        final var response = restTemplate.getForEntity(properUrl.toUri(), ProductReview.class);
        if (!response.getStatusCode().is2xxSuccessful() || !response.hasBody()) {
            return null;
        }
        final var responseReview = response.getBody();
        return new ReviewResponse(
                responseReview.getProductId(),
                responseReview.getProvider(),
                responseReview.getReviews().entrySet().stream().map(entry -> new ReviewResponse.Review(entry.getKey(), entry.getValue())).collect(Collectors.toList())
        );
    }

    public void kill(final String serviceProvider) {
        final var url = discovery.getService(ServiceType.REVIEWS);
        final var properUrl = UriComponentsBuilder.fromHttpUrl(url)
                .pathSegment(REVIEWS_PATH, "health", "kill")
                .queryParam("provider", serviceProvider)
                .build();

        restTemplate.getForEntity(properUrl.toUri(), String.class);
    }

    public void register(final String serviceProvider) {
        final var url = discovery.getService(ServiceType.REVIEWS);
        final var properUrl = UriComponentsBuilder.fromHttpUrl(url)
                .pathSegment(REVIEWS_PATH, "health", "register")
                .queryParam("provider", serviceProvider)
                .build();

        restTemplate.getForEntity(properUrl.toUri(), String.class);
    }
}
