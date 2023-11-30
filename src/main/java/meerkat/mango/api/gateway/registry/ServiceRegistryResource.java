package meerkat.mango.api.gateway.registry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceRegistryResource {

    private static final String CORS_HEADER_NAME = "Access-Control-Allow-Origin";
    private static final String CORS_HEADER_VALUE = "*";

    private final ServiceRegistryService serviceRegistryService;

    @Autowired
    public ServiceRegistryResource(ServiceRegistryService serviceRegistryService) {
        this.serviceRegistryService = serviceRegistryService;
    }

    @GetMapping("/health")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok("Hello World");
    }

    @PutMapping(value = "/register-registry", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registerRegistry(@RequestBody RegistryConfigBean registryConfigBean) {
        serviceRegistryService.registerRegistry(registryConfigBean);
        return ResponseEntity.ok()
                .header(CORS_HEADER_NAME, CORS_HEADER_VALUE)
                .body("registered");
    }

    @GetMapping(value = "/verify", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VerifyServiceResponse> verifyService(@RequestParam("service") String service) {
        final var services = serviceRegistryService.verifyService(service);
        if (services == null) {
            ResponseEntity.notFound();
        }
        return ResponseEntity.ok()
                .header(CORS_HEADER_NAME, CORS_HEADER_VALUE)
                .body(services);
    }
}
