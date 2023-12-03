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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Shopping Cart")
@RestController
@RequestMapping("/cart")
public class CartController {

    private static final String CORS_HEADER_NAME = "Access-Control-Allow-Origin";
    private static final String CORS_HEADER_VALUE = "*";
    private final CartService cartService;

    @Autowired
    public CartController(final CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartByUserIdResponse> getCartByUserId(@PathVariable("userId") final String userId) {
        return ResponseEntity.ok().header(CORS_HEADER_NAME, CORS_HEADER_VALUE).body(cartService.getCartByUserId(userId));
    }

    @GetMapping(value = "/{cartId}/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cart> getCartForUser(@PathVariable("cartId") final String cartId,
                                               @PathVariable("userId") final String userId) {
        return ResponseEntity.ok().header(CORS_HEADER_NAME, CORS_HEADER_VALUE).body(cartService.getCart(cartId, userId));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cart> addItemToCart(@RequestBody final Cart cart) {
        return ResponseEntity.ok().header(CORS_HEADER_NAME, CORS_HEADER_VALUE).body(cartService.addItemsToCart(cart));
    }

    @DeleteMapping(value = "/{cartId}/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteItemFromCart(@PathVariable("cartId") final String cartId,
                                                   @PathVariable("userId") final String userId,
                                                   @RequestBody final CartProduct cartProduct) {
        cartService.deleteItemInCart(cartId, userId, cartProduct);
        return ResponseEntity.noContent().header(CORS_HEADER_NAME, CORS_HEADER_VALUE).build();
    }

    @PutMapping(value = "/{cartId}/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> modifyItemInCart(@PathVariable("cartId") final String cartId,
                                                 @PathVariable("userId") final String userId,
                                                 @RequestBody final CartProduct cartProduct) {
        cartService.modifyItemInCart(cartId, userId, cartProduct);
        return ResponseEntity.noContent().header(CORS_HEADER_NAME, CORS_HEADER_VALUE).build();
    }
}
