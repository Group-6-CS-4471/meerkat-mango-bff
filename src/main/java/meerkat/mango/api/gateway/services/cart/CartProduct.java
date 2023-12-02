package meerkat.mango.api.gateway.services.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class CartProduct {

    @JsonProperty("productId")
    private String product_uuid;

    @JsonProperty
    private String provider;

    @JsonProperty
    private int amount;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof CartProduct)) {
            return false;
        }

        CartProduct cartProduct = (CartProduct) obj;

        return product_uuid.equals(cartProduct.getProduct_uuid()) && provider.equals(cartProduct.getProvider()) && amount == cartProduct.getAmount();
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_uuid, provider, amount);
    }
}

