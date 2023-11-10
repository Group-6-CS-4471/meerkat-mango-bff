package meerkat.mango.api.gateawy;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegistryConfigBean {

    @JsonProperty
    private final String ip;

    @JsonProperty
    private final RegistryType type;
}
