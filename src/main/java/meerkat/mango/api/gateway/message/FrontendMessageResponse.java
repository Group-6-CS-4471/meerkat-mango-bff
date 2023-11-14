package meerkat.mango.api.gateway.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import meerkat.mango.api.gateway.message.inventory.InventoryResponse;
import meerkat.mango.api.gateway.message.orders.OrderResponse;
import meerkat.mango.api.gateway.message.search.SearchResponse;

@Data
@Builder
public class FrontendMessageResponse {

    @JsonProperty
    private final String id;

    @JsonProperty
    private OrderResponse orderResponse;

    @JsonProperty
    private InventoryResponse inventoryResponse;

    @JsonProperty
    private SearchResponse searchResponse;
}
