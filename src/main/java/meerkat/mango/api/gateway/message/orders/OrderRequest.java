package meerkat.mango.api.gateway.message.orders;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Setter
@Getter
public class OrderRequest {

    private final String message;
}
