package meerkat.mango.api.gateway.services.cart;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Shopping Cart")
@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(final CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartByUserIdResponse> getCartByUserId(@PathVariable("userId") final String userId) {
        return ResponseEntity.ok().body(cartService.getCartByUserId(userId));
    }

    @GetMapping(value = "/{cartId}/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cart> getCartForUser(@PathVariable("cartId") final String cartId,
                                               @PathVariable("userId") final String userId) {
        return ResponseEntity.ok().body(cartService.getCart(cartId, userId));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cart> addItemToCart(@RequestBody final AddToCartRequest cart) {
        return ResponseEntity.ok().body(cartService.addItemsToCart(cart));
    }

    @DeleteMapping(value = "/{cartId}/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteItemFromCart(@PathVariable("cartId") final String cartId,
                                                   @PathVariable("userId") final String userId,
                                                   @RequestBody final CartProduct cartProduct) {
        cartService.deleteItemInCart(cartId, userId, cartProduct);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{cartId}/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> modifyItemInCart(@PathVariable("cartId") final String cartId,
                                                 @PathVariable("userId") final String userId,
                                                 @RequestBody final CartProduct cartProduct) {
        cartService.modifyItemInCart(cartId, userId, cartProduct);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/health/kill", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> killProvider(@RequestParam(value = "provider") final String provider) {
        cartService.kill(provider);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/health/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registerServiceProvider(@RequestParam(value = "provider") final String provider) {
        cartService.register(provider);
        return ResponseEntity.noContent().build();
    }
}
