package meerkat.mango.api.gateawy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeerkatMangoGatewayResource {
    private final MeerkatMangoGatewayService meerkatMangoGatewayService;

    @Autowired
    public MeerkatMangoGatewayResource(MeerkatMangoGatewayService meerkatMangoGatewayService) {
        this.meerkatMangoGatewayService = meerkatMangoGatewayService;
    }

    @GetMapping("/")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok("Hello World");
    }

    @PutMapping("/register-registry")
    public ResponseEntity<String> registerRegistry(@RequestBody RegistryConfigBean registryConfigBean) {
        meerkatMangoGatewayService.registerRegistry(registryConfigBean);
        return ResponseEntity.ok("registered");
    }

    @GetMapping("/verify")
    public ResponseEntity<Boolean> verifyService(@RequestParam("service") String service) {
        final var isAlive = meerkatMangoGatewayService.verifyService(service);
        return ResponseEntity.ok(isAlive);
    }
}
