package meerkat.mango.api.gateway.services.checkout;

import meerkat.mango.api.gateway.resttemplate.CustomClientHttpRequestFactory;
import meerkat.mango.api.gateway.services.Discovery;
import meerkat.mango.api.gateway.services.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@Service
public class CheckoutService {
    private final RestTemplate restTemplate;
    private final Discovery discovery;
    private static final String CHECKOUT_PATH = "checkout";

    @Autowired
    public CheckoutService(final Discovery discovery) {
        this.discovery = discovery;
        this.restTemplate = new RestTemplateBuilder()
                .setConnectTimeout(Duration.of(2, ChronoUnit.SECONDS))
                .setReadTimeout(Duration.of(2, ChronoUnit.SECONDS))
                .requestFactory(CustomClientHttpRequestFactory.class)
                .build();
    }

    public Boolean lowerStock(final String productId,
                              final String provider,
                              int amount,
                              final String cartId,
                              final String userId) {
        final var url = discovery.getService(ServiceType.REVIEWS);
        if (url == null) {
            return null;
        }
        final var properUrl = UriComponentsBuilder.fromHttpUrl(url)
                .pathSegment(CHECKOUT_PATH, "lower-stock", productId, provider)
                .queryParam("amount", amount)
                .queryParam("cartId", cartId)
                .queryParam("userId", userId)
                .build();

        return restTemplate.getForEntity(properUrl.toUri(), Boolean.class).getBody();
    }
}