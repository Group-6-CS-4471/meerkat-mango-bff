package meerkat.mango.api.gateway.registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
@Component
public class ServiceRegistryService {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceRegistryService.class);
    private static final String SERVICE_PATH = "verify";
    private static final String SERVICE_QUERY = "service";
    private final RestTemplate restTemplate;
    private static final Map<RegistryType, String> REGISTRY_URLS = Map.of(RegistryType.MAIN, "localhost", RegistryType.BACKUP, "localhost");

    public ServiceRegistryService() {
        this.restTemplate = new RestTemplate();
    }

    public VerifyServiceResponse verifyService(final String service) {
        try {
            final var mainRegistryResponse = callServiceRegistry(service, RegistryType.MAIN, 50000);
            if (mainRegistryResponse.getStatusCode().is2xxSuccessful()) {
                return mainRegistryResponse.getBody();
            }
        } catch (ServiceRegistryNotFoundException e) {
            try {
                final var backupResponse = callServiceRegistry(service, RegistryType.BACKUP, 50001);
                if (!backupResponse.getStatusCode().is2xxSuccessful()) {
                    LOG.error("Service registries are unavailable. This isn't right.");
                }
                return backupResponse.getBody();
            } catch (ServiceRegistryNotFoundException e2) {
                return null;
            }
        }
        return null;
    }

    private ResponseEntity<VerifyServiceResponse> callServiceRegistry(final String service, final RegistryType type, final int port) {
        final var uri = UriComponentsBuilder.newInstance()
                .host(REGISTRY_URLS.get(type))
                .port(port)
                .path(SERVICE_PATH)
                .queryParam(SERVICE_QUERY, service);

        try {
            return restTemplate.getForEntity("http:" + uri.toUriString(), VerifyServiceResponse.class);
        } catch (ResourceAccessException e) {
            throw new ServiceRegistryNotFoundException();
        }

    }
}
