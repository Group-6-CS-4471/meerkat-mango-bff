package meerkat.mango.api.gateway.message.serviceresolution;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import meerkat.mango.api.gateway.message.serviceresolution.inventory.InventoryResponse;
import meerkat.mango.api.gateway.message.serviceresolution.cart.ShoppingCartResponse;
import meerkat.mango.api.gateway.message.serviceresolution.search.SearchResponse;

@Data
@Builder
public class FrontendMessageResponse {

    @JsonProperty
    private final String id;

    @JsonProperty
    private final String trace;

    @JsonProperty
    private ShoppingCartResponse orderResponse;

    @JsonProperty
    private InventoryResponse inventoryResponse;

    @JsonProperty
    private SearchResponse searchResponse;
}
