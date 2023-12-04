package meerkat.mango.api.gateway.services.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class AddToCartRequest {

    @JsonProperty
    private final String cartId;

    @JsonProperty
    private final String userId;

    @JsonProperty
    private final List<CartProduct> products;

}