package meerkat.mango.api.gateway.services.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Builder
public class GetItemResponse {

    @JsonProperty
    private final String productId;

    @JsonProperty
    private final String provider;

    @JsonProperty
    private final String name;

    @JsonProperty
    private final int stock;

    @JsonProperty
    private final double retailPrice;

    @JsonProperty
    private final int discount;

    @JsonProperty
    private final List<String> images;

    @JsonProperty
    private final String description;
}
