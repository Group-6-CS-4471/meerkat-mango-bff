package meerkat.mango.api.gateway.services.checkout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    @Autowired
    public CheckoutController(final CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @GetMapping(value = "/lower-stock/{productId}/{provider}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean lowerStock(@PathVariable("productId") final String productId,
                              @PathVariable("provider") final String provider,
                              @RequestParam("amount") final int amount) {
        return checkoutService.lowerStock(productId, provider, amount);
    }
}
