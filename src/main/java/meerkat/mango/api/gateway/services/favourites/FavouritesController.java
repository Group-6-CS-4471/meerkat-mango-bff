package meerkat.mango.api.gateway.services.favourites;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("favourites")
public class FavouritesController {
    private final FavouritesService favouritesService;

    @Autowired
    public FavouritesController(final FavouritesService favouritesService) {
        this.favouritesService = favouritesService;
    }

    @GetMapping(value = "/{userId}")
    @ResponseBody
    public List<FavouritesListResponse> getFavouritesList(@PathVariable("userId") final String userId) {
        return favouritesService.getFavourites(userId);
    }

    @PostMapping(value = "/{userId}/{provider}")
    public Favourites addFavourites(@PathVariable("userId") final String userId,
                                    @PathVariable("provider") final String provider,
                                    @RequestBody final List<FavouritesItem> favouritesItems) {
        return favouritesService.addFavourite(userId, provider, favouritesItems);
    }

    @PostMapping(value = "/delete/{userId}/{provider}")
    public Favourites deleteFavourites(@PathVariable("userId") final String userId,
                                    @PathVariable("provider") final String provider,
                                    @RequestBody final List<FavouritesItem> favouritesItems) {
        return favouritesService.removeFavourite(userId, provider, favouritesItems);
    }

}
