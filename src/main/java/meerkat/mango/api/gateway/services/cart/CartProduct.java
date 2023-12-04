package meerkat.mango.api.gateway.services.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class CartProduct {

    @JsonProperty("productId")
    private final String productId;

    @JsonProperty
    private final String provider;

    @JsonProperty
    private int amount;

    @JsonProperty
    private final String image;

    @JsonProperty
    private final String name;

    @JsonProperty
    private final Double price;
}

