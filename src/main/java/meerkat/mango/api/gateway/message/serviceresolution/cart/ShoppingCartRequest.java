package meerkat.mango.api.gateway.message.serviceresolution.cart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import meerkat.mango.api.gateway.message.serviceresolution.Action;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShoppingCartRequest {

    @JsonProperty
    private final long cartId;

    @JsonProperty
    private final Action action;

    @JsonProperty
    private Long itemId;

    @JsonProperty
    private Integer amount;
}
