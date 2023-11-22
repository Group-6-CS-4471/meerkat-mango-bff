package meerkat.mango.api.gateway.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import meerkat.mango.api.gateway.message.inventory.InventoryRequest;
import meerkat.mango.api.gateway.message.orders.OrderRequest;
import meerkat.mango.api.gateway.message.search.SearchRequest;

@Data
public class FrontendMessageRequest {

    @JsonProperty
    private final String id;

    @JsonProperty
    private final ServiceNames service;

    @JsonProperty
    private OrderRequest orderRequest;

    @JsonProperty
    private InventoryRequest inventoryRequest;

    @JsonProperty
    private SearchRequest searchRequest;
}
