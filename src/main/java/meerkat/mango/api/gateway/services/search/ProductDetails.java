package meerkat.mango.api.gateway.services.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class ProductDetails {
    @JsonProperty
    private final String name;

    @JsonProperty
    private final int stock;

    @JsonProperty("retail_price")
    private final double retailPrice;

    @JsonProperty
    private final int discount;

    @JsonProperty
    private final List<String> images;

    @JsonProperty
    private final String description;
}
