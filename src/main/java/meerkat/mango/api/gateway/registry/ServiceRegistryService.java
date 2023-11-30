package meerkat.mango.api.gateway.registry;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@Component
public class ServiceRegistryService {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceRegistryService.class);
    private static final String USER_AGENT = "meerkat-bff";
    private static final String SERVICE_PATH = "verify";
    private static final String SERVICE_QUERY = "service";
    private final RestTemplate restTemplate;
    private final Map<RegistryType, String> registryUrls;

    public ServiceRegistryService() {
        this.restTemplate = new RestTemplate();
        this.registryUrls = Map.of(RegistryType.MAIN, "localhost");
    }

    public void registerRegistry(RegistryConfigBean registryConfigBean) {
        registryUrls.put(registryConfigBean.getType(), registryConfigBean.getIp());
    }

    public VerifyServiceResponse verifyService(final String service) {
        final var mainRegistryResponse = callServiceRegistry(service, RegistryType.MAIN);
        if (mainRegistryResponse.getStatusCode().is2xxSuccessful()) {
            return mainRegistryResponse.getBody();
        }

        final var backupResponse = callServiceRegistry(service, RegistryType.BACKUP);
        if (!backupResponse.getStatusCode().is2xxSuccessful()) {
            LOG.error("Service registries are unavailable. This isn't right.");
            return null;
        }

        return backupResponse.getBody();
    }

    private ResponseEntity<VerifyServiceResponse> callServiceRegistry(final String service, final RegistryType type) {
        final var uri = UriComponentsBuilder.newInstance()
                .host(registryUrls.get(type))
                .port("50000")
                .path(SERVICE_PATH)
                .queryParam(SERVICE_QUERY, service);

        return restTemplate.getForEntity("http:" + uri.toUriString(), VerifyServiceResponse.class);
    }
}
