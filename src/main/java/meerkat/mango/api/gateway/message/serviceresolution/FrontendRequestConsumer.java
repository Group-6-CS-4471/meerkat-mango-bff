package meerkat.mango.api.gateway.message.serviceresolution;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class FrontendRequestConsumer {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Logger LOG = LoggerFactory.getLogger(FrontendRequestConsumer.class);
    private final SqsMessenger sqsMessenger;

    @Autowired
    public FrontendRequestConsumer(final SqsMessenger sqsMessenger) {
        this.sqsMessenger = sqsMessenger;
    }

    public Optional<FrontendMessageResponse> accept(FrontendMessageRequest frontendMessageRequest) {
        try {
            final var trace = UUID.randomUUID().toString();
            final var queueName = frontendMessageRequest.getServiceName().getQueueName();
            frontendMessageRequest.setTrace(trace);
            sqsMessenger.sendMessageToQueue(OBJECT_MAPPER.writeValueAsString(frontendMessageRequest), queueName);
            return sqsMessenger.receiveMessageFromQueue(trace, queueName);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
