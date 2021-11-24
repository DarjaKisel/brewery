package com.dzinevich.brewery.events;

import com.dzinevich.brewery.web.model.BeerDtoV2;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent {
    public BrewBeerEvent(BeerDtoV2 beerDto) {
        super(beerDto);
    }
}
