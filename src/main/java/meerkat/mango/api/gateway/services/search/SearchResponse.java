package meerkat.mango.api.gateway.services.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class SearchResponse {

    @JsonProperty
    private final List<Product> products;
}
