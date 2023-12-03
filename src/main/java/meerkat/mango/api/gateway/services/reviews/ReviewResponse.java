package meerkat.mango.api.gateway.services.reviews;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class ReviewResponse {

    @JsonProperty
    private final String productId;

    @JsonProperty
    private final String provider;

    @JsonProperty
    private final List<Review> reviews;

    @Getter
    @RequiredArgsConstructor
    @NoArgsConstructor(force = true)
    static class Review {

        @JsonProperty
        private final String name;

        @JsonProperty
        private final String review;
    }
}
