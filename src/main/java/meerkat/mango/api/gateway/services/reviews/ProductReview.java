package meerkat.mango.api.gateway.services.reviews;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class ProductReview {

    @JsonProperty
    private final String productId;

    @JsonProperty
    private final String provider;

    @JsonProperty
    private final Map<String, String> reviews;
}
