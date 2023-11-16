package meerkat.mango.api.gateway.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageResource {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @PostMapping(value = "/send", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody FrontendMessageResponse sendMessage(@RequestBody FrontendMessageRequest frontendMessageRequest) {
        final var type = frontendMessageRequest.getService().getType();
        final var consumer = frontendMessageRequest.getService().getConsumer();
        consumer.accept(frontendMessageRequest);
        return null;
    }
}
