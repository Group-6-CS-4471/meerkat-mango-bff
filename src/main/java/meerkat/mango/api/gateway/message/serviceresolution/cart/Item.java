package meerkat.mango.api.gateway.message.serviceresolution.cart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    @JsonProperty
    private final long itemId;

    @JsonProperty
    private final String title;

    @JsonProperty
    private final Double price;

    @JsonProperty
    private final int quantity;

    @JsonProperty
    private final String imageUrl;
}
