package meerkat.mango.api.gateway.registry;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class ServiceUrl {

    @JsonProperty
    private final String ip;

    @JsonProperty
    private final String port;

    @JsonProperty
    private final String path;
}