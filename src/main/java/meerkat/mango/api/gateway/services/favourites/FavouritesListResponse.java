package meerkat.mango.api.gateway.services.favourites;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class FavouritesListResponse {

    @JsonProperty
    private final String provider;

    @JsonProperty
    private final List<FavouritesItem> favourites;
}