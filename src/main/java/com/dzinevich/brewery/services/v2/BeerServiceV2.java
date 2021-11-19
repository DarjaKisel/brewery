package com.dzinevich.brewery.services.v2;

import com.dzinevich.brewery.web.model.v2.BeerDtoV2;

import java.util.UUID;

public interface BeerServiceV2 {
    BeerDtoV2 getBeerById(UUID id);
    BeerDtoV2 addNewBeer(BeerDtoV2 beerDto);
    BeerDtoV2 updateBeer(UUID id, BeerDtoV2 beerDto);
    void deleteBeer(UUID id);
}
