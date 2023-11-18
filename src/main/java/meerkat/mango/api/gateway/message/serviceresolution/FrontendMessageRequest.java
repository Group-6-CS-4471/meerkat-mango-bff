package meerkat.mango.api.gateway.message.serviceresolution;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import meerkat.mango.api.gateway.message.serviceresolution.inventory.InventoryRequest;
import meerkat.mango.api.gateway.message.serviceresolution.cart.ShoppingCartRequest;
import meerkat.mango.api.gateway.message.serviceresolution.search.SearchRequest;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FrontendMessageRequest {

    @JsonProperty
    private final String id;

    @JsonProperty
    private final ServiceName serviceName;

    @JsonProperty
    private String trace;

    @JsonProperty
    private ShoppingCartRequest shoppingCartRequest;

    @JsonProperty
    private InventoryRequest inventoryRequest;

    @JsonProperty
    private SearchRequest searchRequest;
}
