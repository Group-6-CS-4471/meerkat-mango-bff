package meerkat.mango.api.gateway.services.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import meerkat.mango.api.gateway.services.Discovery;
import meerkat.mango.api.gateway.services.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private static final String SEARCH_PATH = "/search";
    private final RestTemplate restTemplate;
    private final Discovery discovery;

    @Autowired
    public SearchService(final Discovery discovery) {
        this.restTemplate = new RestTemplateBuilder()
                .setConnectTimeout(Duration.of(2, ChronoUnit.SECONDS))
                .setReadTimeout(Duration.of(2, ChronoUnit.SECONDS))
                .build();
        this.discovery = discovery;
    }


    public List<SearchResponse> search(final List<String> keywords) {
        final var url = discovery.getService(ServiceType.SEARCH);
        final var properUrl = UriComponentsBuilder.fromHttpUrl(url).path(SEARCH_PATH).queryParam("keyword", keywords).build();
        final var response = restTemplate.getForEntity(properUrl.toUri(), BearerSearchResponse.class);

        if (!response.hasBody() || !response.getStatusCode().is2xxSuccessful()) {
            return null;
        }
        final var bearerBody = response.getBody();
        return bearerBody.products.stream().map(p -> {
            final var details = p.getDetails();
            return SearchResponse.builder()
                    .productId(p.getProductId())
                    .provider(p.getProvider())
                    .currentPrice(details.getRetailPrice() * (1 - details.getDiscount()))
                    .normalPrice(details.getRetailPrice())
                    .imageUrl(details.getImages().get(0))
                    .build();
        }).collect(Collectors.toList());
    }

    @Getter
    @RequiredArgsConstructor
    @NoArgsConstructor(force = true)
    static class BearerSearchResponse {

        @JsonProperty
        private final List<Product> products;
    }

}
