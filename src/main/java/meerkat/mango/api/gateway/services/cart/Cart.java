package meerkat.mango.api.gateway.services.cart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cart {

    @JsonProperty
    private final String cartId;

    @JsonProperty
    private final String userId;

    @JsonProperty
    @Setter
    private List<ProductDbo> products;

    @JsonProperty
    @Setter
    private Double total;

    @Getter
    @RequiredArgsConstructor
    @NoArgsConstructor(force = true)
    static class ProductDbo {

        @JsonProperty
        private final ProductIdentifier productIdentifier;

        @JsonProperty
        private final CartProduct cartProduct;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor(force = true)
    static class ProductIdentifier {

        @JsonProperty("productId")
        private String product_uuid;

        @JsonProperty
        private String provider;

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }

            if (!(obj instanceof ProductIdentifier)) {
                return false;
            }

            ProductIdentifier productIdentifier = (ProductIdentifier) obj;

            return product_uuid.equals(productIdentifier.getProduct_uuid()) && provider.equals(productIdentifier.getProvider());
        }

        @Override
        public int hashCode() {
            return Objects.hash(product_uuid, provider);
        }
    }
}
