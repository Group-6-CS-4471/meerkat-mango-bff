package meerkat.mango.api.gateway.services;

import meerkat.mango.api.gateway.registry.RegistryType;
import meerkat.mango.api.gateway.registry.ServiceRegistryService;
import meerkat.mango.api.gateway.registry.VerifyServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class Discovery {

    private static final Logger LOG = LoggerFactory.getLogger(Discovery.class);
    private final ServiceRegistryService serviceRegistryService;
    private final ExecutorService executorService;
    private final Map<ServiceType, String> urlCache;

    @Autowired
    public Discovery(final ServiceRegistryService serviceRegistryService) {
        this.serviceRegistryService = serviceRegistryService;
        this.executorService = Executors.newSingleThreadExecutor();
        this.urlCache = new HashMap<>();
    }

    public String getService(final ServiceType type) {
        if (urlCache.containsKey(type)) {
            return urlCache.get(type);
        }
        final var services = serviceRegistryService.verifyService(type.getServiceName());

        if (services == null || services.getServiceProviders() == null || services.getServiceProviders().isEmpty()) {
            LOG.error("No service providers for {} are available.", type.getServiceName());
            return null;
        }

        return services.getServiceProviders().values().stream().map(serviceUrl -> {
            final var uri = UriComponentsBuilder.newInstance()
                    .host(serviceUrl.getIp())
                    .port(serviceUrl.getPort());
            return "http:" + uri.toUriString();
        }).findAny().get();
    }

    @Scheduled(initialDelay = 10000, fixedDelay = 30000)
    private void loadCache() {
        LOG.debug("reloading cache");
        for (final var type : ServiceType.values()) {
            final var services = serviceRegistryService.verifyService(type.getServiceName());

            if (services == null || services.getServiceProviders() == null || services.getServiceProviders().isEmpty()) {
                LOG.error("No service providers for {} are available.", type.getServiceName());
                continue;
            }

            urlCache.put(type, services.getServiceProviders().values().stream().map(serviceUrl -> {
                final var uri = UriComponentsBuilder.newInstance()
                        .host(serviceUrl.getIp())
                        .port(serviceUrl.getPort());
                return "http:" + uri.toUriString();
            }).findAny().get());
        }
    }
}
