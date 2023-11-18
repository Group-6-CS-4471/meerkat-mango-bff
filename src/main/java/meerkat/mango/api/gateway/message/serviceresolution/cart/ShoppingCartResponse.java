package meerkat.mango.api.gateway.message.serviceresolution.cart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShoppingCartResponse {

    @JsonProperty
    private final long cartId;

    @JsonProperty
    private final List<Item> items;
}
