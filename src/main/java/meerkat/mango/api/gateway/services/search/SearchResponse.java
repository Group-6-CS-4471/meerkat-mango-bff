package meerkat.mango.api.gateway.services.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class SearchResponse {

    @JsonProperty
    private final String productId;

    @JsonProperty
    private final String provider;

    @JsonProperty
    private final Double currentPrice;

    @JsonProperty
    private final Double normalPrice;

    @JsonProperty
    private final String imageUrl;
}
