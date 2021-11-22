package com.dzinevich.brewery.services;

import com.dzinevich.brewery.web.model.BeerPageableList;
import com.dzinevich.brewery.web.model.Style;
import com.dzinevich.brewery.web.model.v2.BeerDtoV2;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerServiceV2 {
    BeerPageableList getBeerList(String name, Style style, PageRequest pageRequest, boolean showInventoryOnHand);
    BeerDtoV2 getBeerById(UUID id, boolean showInventoryOnHand);
    BeerDtoV2 addNewBeer(BeerDtoV2 beerDto);
    BeerDtoV2 updateBeer(UUID id, BeerDtoV2 beerDto);
    void deleteBeer(UUID id);
}
