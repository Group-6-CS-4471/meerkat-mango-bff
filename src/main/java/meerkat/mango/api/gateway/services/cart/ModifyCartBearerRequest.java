package meerkat.mango.api.gateway.services.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class ModifyCartBearerRequest {

    @JsonProperty
    private final String cartId;

    @JsonProperty
    private final String userId;

    @JsonProperty
    private final Cart.ProductIdentifier product;

    @JsonProperty
    private final int amount;
}
