package meerkat.mango.api.gateway.services.favourites;

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
import java.util.List;

@Service
public class FavouritesService {

    private static final String FAVOURITES_PATH = "favourites";
    private final RestTemplate restTemplate;
    private final Discovery discovery;

    @Autowired
    public FavouritesService(final Discovery discovery) {
        this.discovery = discovery;
        this.restTemplate = new RestTemplateBuilder()
                .setConnectTimeout(Duration.of(2, ChronoUnit.SECONDS))
                .setReadTimeout(Duration.of(2, ChronoUnit.SECONDS))
                .requestFactory(CustomClientHttpRequestFactory.class)
                .build();
    }

    public Favourites addFavourite(String userId, String provider, List<FavouritesItem> favouritesItems) {
        final var url = discovery.getService(ServiceType.REVIEWS);
        if (url == null) {
            return null;
        }

        final var properUrl = UriComponentsBuilder.fromHttpUrl(url).pathSegment(FAVOURITES_PATH, userId, provider).build();

        return restTemplate.postForObject(properUrl.toUri(), favouritesItems, Favourites.class);
    }

    public List<FavouritesListResponse> getFavourites(String userId) {
        final var url = discovery.getService(ServiceType.REVIEWS);
        if (url == null) {
            return null;
        }
        final var properUrl = UriComponentsBuilder.fromHttpUrl(url).pathSegment(FAVOURITES_PATH, userId).build();
        final var response = restTemplate.getForEntity(properUrl.toUri(), List.class);
        if (!response.getStatusCode().is2xxSuccessful() || !response.hasBody() || response.getBody() == null) {
            return null;
        }
        return response.getBody();
    }

    public Favourites removeFavourite(String userId, String provider, List<FavouritesItem> favouritesItems) {
        final var url = discovery.getService(ServiceType.REVIEWS);
        if (url == null) {
            return null;
        }

        final var properUrl = UriComponentsBuilder.fromHttpUrl(url).pathSegment(FAVOURITES_PATH, "delete", userId, provider).build();

        return restTemplate.postForObject(properUrl.toUri(), favouritesItems, Favourites.class);
    }
}