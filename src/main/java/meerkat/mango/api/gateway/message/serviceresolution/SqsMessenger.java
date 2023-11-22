package meerkat.mango.api.gateway.message.serviceresolution;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.CreateQueueRequest;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SqsException;

import java.util.Objects;
import java.util.Optional;

@Component
public class SqsMessenger {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Logger LOG = LoggerFactory.getLogger(SqsMessenger.class);
    private final SqsClient sqs;

    @Autowired
    public SqsMessenger(final SqsClient sqsClient) {
        this.sqs = sqsClient;

    }

    public void sendMessageToQueue(final String message, final String queueName) throws SqsException {
        final CreateQueueRequest request = CreateQueueRequest.builder()
                .queueName(queueName)
                .build();
        this.sqs.createQueue(request);

        final String url = getQueueUrl(queueName);

        final var sendRequest = SendMessageRequest.builder()
                .queueUrl(url)
                .messageBody(message)
                .build();

        this.sqs.sendMessage(sendRequest);
    }

    public Optional<FrontendMessageResponse> receiveMessageFromQueue(final String trace, final String queueName) {
        final String url = getQueueUrl(queueName);

        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(url)
                .waitTimeSeconds(1)
                .build();

        final var message = this.sqs.receiveMessage(receiveMessageRequest);

        if (!message.hasMessages()) {
            return Optional.empty();
        }

        return message.messages().stream()
                .map(m -> {
                    try {
                        return OBJECT_MAPPER.readValue(m.body(), FrontendMessageResponse.class);
                    } catch (JsonProcessingException e) {
                        LOG.error("Could not convert message with id {} and body {} to FrontendMessageResponse", m.messageId(), m.body());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .filter(response -> trace.equals(response.getTrace()))
                .findFirst();
    }

    private String getQueueUrl(final String queueName) {
        final var queueUrlRequest = GetQueueUrlRequest.builder()
                .queueName(queueName)
                .build();

        return this.sqs.getQueueUrl(queueUrlRequest).queueUrl();
    }

}
