package com.dzinevich.brewery.services.brewing;

import com.dzinevich.brewery.events.BrewBeerEvent;

public interface BrewBeerListener {
    void receiveBrewBeer(BrewBeerEvent brewBeerEvent);
}
