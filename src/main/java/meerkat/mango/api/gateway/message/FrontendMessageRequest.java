package meerkat.mango.api.gateway.message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import meerkat.mango.api.gateway.message.inventory.InventoryRequest;
import meerkat.mango.api.gateway.message.orders.OrderRequest;
import meerkat.mango.api.gateway.message.search.SearchRequest;

@Setter
@Getter
@AllArgsConstructor
public class FrontendMessageRequest {

    private final String id;

    private final ServiceNames service;

    private OrderRequest orderRequest;

    private InventoryRequest inventoryRequest;

    private SearchRequest searchRequest;
}
