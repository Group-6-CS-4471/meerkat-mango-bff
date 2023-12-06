package meerkat.mango.api.gateway.services;

public enum ServiceType {
    SEARCH("search"),
    CHECKOUT("checkout"),
    CART("cart"),
    FAVOURITES("favourites"),
    REVIEWS("reviews");

    private final String serviceName;

    ServiceType(final String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }
}
