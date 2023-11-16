package meerkat.mango.api.gateway.message;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

@Import(SqsCredentialsConfig.class)
public abstract class SqsMessenger {

    protected final String endpoint;

    protected final AmazonSQS sqs;

    @Autowired
    public SqsMessenger(final String endpoint, final AWSCredentialsProvider credentials) {
        this.endpoint = endpoint;
        this.sqs = AmazonSQSClientBuilder.standard()
                .withCredentials(credentials)
                .build();
    }

    protected String getEndpoint() {
        return this.endpoint;
    }

    public void sendMessageToQueue(final String message) {
        final SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(this.endpoint)
                .withMessageBody(message);

       sqs.sendMessage(sendMessageRequest);
    }

    public abstract FrontendMessageResponse receiveMessageFromQueue();

}
