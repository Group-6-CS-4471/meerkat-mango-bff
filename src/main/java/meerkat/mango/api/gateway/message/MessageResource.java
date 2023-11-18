package meerkat.mango.api.gateway.message;

import meerkat.mango.api.gateway.message.serviceresolution.FrontendMessageRequest;
import meerkat.mango.api.gateway.message.serviceresolution.FrontendMessageResponse;
import meerkat.mango.api.gateway.message.serviceresolution.FrontendRequestConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/message")
public class MessageResource {

    private static final String NO_RESPONSE_ERROR = "No service could pick up the request, please try again.";

    private final FrontendRequestConsumer frontendRequestConsumer;

    @Autowired
    public MessageResource(final FrontendRequestConsumer frontendRequestConsumer) {
        this.frontendRequestConsumer = frontendRequestConsumer;
    }


    @PostMapping(value = "/send", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody FrontendMessageResponse sendMessage(@RequestBody FrontendMessageRequest frontendMessageRequest) {
        final var response = frontendRequestConsumer.accept(frontendMessageRequest);

        if (response.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, NO_RESPONSE_ERROR);
        }
        return response.get();
    }
}
