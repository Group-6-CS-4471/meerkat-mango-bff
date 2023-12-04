package meerkat.mango.api.gateway.services.cart;

import meerkat.mango.api.gateway.resttemplate.CustomClientHttpRequestFactory;
import meerkat.mango.api.gateway.services.Discovery;
import meerkat.mango.api.gateway.services.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Service
public class CartService {

    private static final String CART_PATH = "cart";
    private final RestTemplate restTemplate;
    private final Discovery discovery;

    @Autowired
    public CartService(final Discovery discovery) {
        this.restTemplate = new RestTemplateBuilder()
                .setConnectTimeout(Duration.of(2, ChronoUnit.SECONDS))
                .setReadTimeout(Duration.of(2, ChronoUnit.SECONDS))
                .requestFactory(CustomClientHttpRequestFactory.class)
                .build();
        this.discovery = discovery;
    }

    public CartByUserIdResponse getCartByUserId(final String userId) {
        final var url = discovery.getService(ServiceType.CART);
        final var properUrl = UriComponentsBuilder.fromHttpUrl(url).pathSegment(CART_PATH, userId).build();
        final var response = restTemplate.getForEntity(properUrl.toUri(), String.class);
        if (!response.hasBody() || !response.getStatusCode().is2xxSuccessful()) {
            return null;
        }
        return new CartByUserIdResponse(response.getBody());
    }

    public Cart getCart(final String cartId, final String userId) {
        final var url = discovery.getService(ServiceType.CART);
        final var properUrl = UriComponentsBuilder.fromHttpUrl(url).pathSegment(CART_PATH, cartId, userId).build();
        final var response = restTemplate.getForEntity(properUrl.toUri(), Cart.class);

        if (!response.hasBody() || !response.getStatusCode().is2xxSuccessful()) {
            return null;
        }

        final var cart = response.getBody();

        cart.setTotal(cart.getProducts().stream().map(p -> p.getCartProduct().getPrice() * p.getCartProduct().getAmount()).reduce(Double::sum).get());

        return cart;
    }

    public Cart addItemsToCart(final AddToCartRequest addToCartRequest) {
        final var url = discovery.getService(ServiceType.CART);
        final var properUrl = UriComponentsBuilder.fromHttpUrl(url).pathSegment(CART_PATH).build();
        final var response = restTemplate.postForObject(properUrl.toUri(), addToCartRequest, Cart.class);
        if (response == null) {
            return response;
        }

        response.setTotal(response.getProducts().stream().map(p -> p.getCartProduct().getPrice() * p.getCartProduct().getAmount()).reduce(Double::sum).get());

        return response;
    }

    public void deleteItemInCart(final String cartId, final String userId, final CartProduct cartProduct) {
        final var url = discovery.getService(ServiceType.CART);
        final var properUrl = UriComponentsBuilder.fromHttpUrl(url)
                .pathSegment(CART_PATH, cartId, userId)
                .queryParam("productId", cartProduct.getProductId())
                .queryParam("provider", cartProduct.getProvider())
                .build();
        restTemplate.exchange(properUrl.toUri(), HttpMethod.DELETE, new HttpEntity<>(cartProduct), Void.class);
    }

    public void modifyItemInCart(final String cartId, final String userId, final CartProduct cartProduct) {
        final var url = discovery.getService(ServiceType.CART);
        final var modifyCartRequest = new ModifyCartBearerRequest(cartId, userId, new Cart.ProductIdentifier(cartProduct.getProductId(), cartProduct.getProvider()), cartProduct.getAmount());
        final var properUrl = UriComponentsBuilder.fromHttpUrl(url).pathSegment(CART_PATH).build();
        restTemplate.put(properUrl.toUri(), new HttpEntity<>(modifyCartRequest));
    }
}
