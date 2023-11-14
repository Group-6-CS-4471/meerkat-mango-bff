package meerkat.mango.api.gateway.message;

import meerkat.mango.api.gateway.message.inventory.InventoryConsumer;
import meerkat.mango.api.gateway.message.inventory.InventoryRequest;
import meerkat.mango.api.gateway.message.orders.OrderConsumer;
import meerkat.mango.api.gateway.message.orders.OrderRequest;
import meerkat.mango.api.gateway.message.search.SearchConsumer;
import meerkat.mango.api.gateway.message.search.SearchRequest;

public enum ServiceNames {

    OS("order", new OrderConsumer(), OrderRequest.class),
    INV("inventory", new InventoryConsumer(), InventoryRequest.class),
    ISS("search", new SearchConsumer(), SearchRequest.class);

    private final String topic;
    private final FrontendRequestConsumer consumer;
    private final Class type;

    ServiceNames(final String topic, FrontendRequestConsumer consumer, Class type) {
        this.topic = topic;
        this.consumer = consumer;
        this.type = type;
    }

    public String getTopic() {
        return topic;
    }

    public FrontendRequestConsumer getConsumer() {
        return consumer;
    }

    public Class getType() {
        return type;
    }
}
