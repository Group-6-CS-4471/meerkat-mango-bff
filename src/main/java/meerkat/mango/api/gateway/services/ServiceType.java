package meerkat.mango.api.gateway.services;

public enum ServiceType {
    SEARCH("search"),
    CHECKOUT("checkout"),
    STOCK_NOTIFIER("stockNotifier"),
    PRICE_NOTIFIER("priceChangeNotifier"),
    CART("cart"),
    REVIEWS("reviews");

    private final String serviceName;

    ServiceType(final String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }
}
