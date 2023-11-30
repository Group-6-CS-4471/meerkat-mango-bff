package meerkat.mango.api.gateway.services.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class Product {

    @JsonProperty("product_uuid")
    private final String productId;

    @JsonProperty
    private final String provider;

    @JsonProperty
    private final ProductDetails details;

}
