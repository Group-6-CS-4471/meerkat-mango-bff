package meerkat.mango.api.gateway.registry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceRegistryResource {
    private final ServiceRegistryService serviceRegistryService;

    @Autowired
    public ServiceRegistryResource(ServiceRegistryService serviceRegistryService) {
        this.serviceRegistryService = serviceRegistryService;
    }

    @GetMapping("/")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok("Hello World");
    }

    @PutMapping("/register-registry")
    public ResponseEntity<String> registerRegistry(@RequestBody RegistryConfigBean registryConfigBean) {
        serviceRegistryService.registerRegistry(registryConfigBean);
        return ResponseEntity.ok("registered");
    }

    @GetMapping("/verify")
    public ResponseEntity<Boolean> verifyService(@RequestParam("service") String service) {
        final var isAlive = serviceRegistryService.verifyService(service);
        return ResponseEntity.ok(isAlive);
    }
}
