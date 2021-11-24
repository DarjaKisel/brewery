package com.dzinevich.brewery.events;

import com.dzinevich.brewery.web.model.BeerDtoV2;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NewInventoryEvent extends BeerEvent {

    public NewInventoryEvent(BeerDtoV2 beerDto) {
        super(beerDto);
    }
}
