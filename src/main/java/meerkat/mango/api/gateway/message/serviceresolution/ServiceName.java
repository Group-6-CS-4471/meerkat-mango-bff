package meerkat.mango.api.gateway.message.serviceresolution;

import lombok.Getter;

public enum ServiceName {
    CART("shopping-cart-request"),
    REVIEWS("reviews-request"),
    HISTORY("history-request"),
    FAVOURITES("favourites-list-request"),
    STOCK("stock-notifier-request"),
    PRICE("price-notifier-request");

    private final String queueName;

    ServiceName(final String queueName) {
        this.queueName = queueName;
    }

    public String getQueueName() {
        return queueName;
    }
}
