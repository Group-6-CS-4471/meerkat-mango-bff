package meerkat.mango.api.gateway.services.favourites;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class FavouritesItem {

    @JsonProperty
    private final String productId;

    @JsonProperty
    private final String image;

    @JsonProperty
    private final String name;

    @JsonProperty
    private final Double price;
}
