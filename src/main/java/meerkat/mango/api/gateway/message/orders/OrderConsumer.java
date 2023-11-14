package meerkat.mango.api.gateway.message.orders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import meerkat.mango.api.gateway.message.FrontendMessageRequest;
import meerkat.mango.api.gateway.message.FrontendRequestConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderConsumer implements FrontendRequestConsumer<FrontendMessageRequest> {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Logger LOG = LoggerFactory.getLogger(OrderConsumer.class);

    @Override
    public void accept(FrontendMessageRequest frontendMessageRequest) {
        try {
            LOG.info("hello" + OBJECT_MAPPER.writeValueAsString(frontendMessageRequest));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
