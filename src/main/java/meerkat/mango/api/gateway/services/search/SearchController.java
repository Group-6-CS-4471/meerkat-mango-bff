package meerkat.mango.api.gateway.services.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private static final ResponseEntity.BodyBuilder OK_RESPONSE = ResponseEntity.ok();
    private final SearchService searchService;

    @Autowired
    public SearchController(final SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SearchResponse>> getProducts(@RequestParam("keyword") final List<String> keyword) {
        return OK_RESPONSE.body(searchService.search(keyword));
    }

    @GetMapping(value = "/{productId}/{provider}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetItemResponse> getItem(@PathVariable("productId") final String productId,
                                                   @PathVariable("provider") final String provider) {
        return OK_RESPONSE.body(searchService.getItem(productId, provider));
    }
}
