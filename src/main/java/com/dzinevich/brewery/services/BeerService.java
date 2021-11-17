package com.dzinevich.brewery.services;

import com.dzinevich.brewery.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {
    BeerDto getBeerById(UUID id);
    BeerDto addNewBeer(BeerDto beerDto);
    void updateBeer(UUID id, BeerDto beerDto);
    void deleteBeer(UUID id);
}
