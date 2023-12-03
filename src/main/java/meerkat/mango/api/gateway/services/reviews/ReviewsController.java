package meerkat.mango.api.gateway.services.reviews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/reviews")
public class ReviewsController {

    private static final String CORS_HEADER_NAME = "Access-Control-Allow-Origin";
    private static final String CORS_HEADER_VALUE = "*";

    private final ReviewsService reviewsService;

    @Autowired
    public ReviewsController(final ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }

    @GetMapping(value = "/{productId}/{provider}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewResponse> getProductReview(@PathVariable("productId") final String productId,
                                                          @PathVariable("provider") final String provider) {
        final var review = reviewsService.getReviews(productId, provider);
        if (review == null) {
            return ResponseEntity.badRequest().header(CORS_HEADER_NAME, CORS_HEADER_VALUE).build();
        }
        return ResponseEntity.ok().header(CORS_HEADER_NAME, CORS_HEADER_VALUE).body(review);
    }
}
