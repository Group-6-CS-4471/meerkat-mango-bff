package meerkat.mango.api.gateway.services.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    private static final String CORS_HEADER_NAME = "Access-Control-Allow-Origin";
    private static final String CORS_HEADER_VALUE = "*";


    private final SearchService searchService;

    @Autowired
    public SearchController(final SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SearchResponse>> getProducts(@RequestParam("keyword") final List<String> keyword) {
        return ResponseEntity.ok().header(CORS_HEADER_NAME, CORS_HEADER_VALUE).body(searchService.search(keyword));
    }
}
