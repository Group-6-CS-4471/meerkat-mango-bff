package meerkat.mango.api.gateway.services.favourites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Favourites {

    private String user_uuid;

    private String provider;

    private List<FavouritesItem> products;
}
