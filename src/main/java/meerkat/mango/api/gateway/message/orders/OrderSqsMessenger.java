package meerkat.mango.api.gateway.message.orders;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import meerkat.mango.api.gateway.message.FrontendMessageResponse;
import meerkat.mango.api.gateway.message.SqsMessenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


public class OrderSqsMessenger extends SqsMessenger {

    @Autowired
    public OrderSqsMessenger(@Value("${order.service.queue}") final String queueUrl, final AWSCredentialsProvider credentials) {
        super(queueUrl, credentials);
    }

    @Override
    public FrontendMessageResponse receiveMessageFromQueue() {
        return null;
    }
}
