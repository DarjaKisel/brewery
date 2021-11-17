package com.dzinevich.brewery.services.v2.impl;

import com.dzinevich.brewery.services.v2.BeerServiceV2;
import com.dzinevich.brewery.web.model.v2.BeerDtoV2;
import com.dzinevich.brewery.web.model.Style;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class BeerServiceV2Impl implements BeerServiceV2 {
    @Override
    public BeerDtoV2 getBeerById(UUID id) {
        return BeerDtoV2.builder()
                .id(UUID.randomUUID())
                .name("Angry IPA")
                .style(Style.IPA)
                .build();
    }

    @Override
    public BeerDtoV2 addNewBeer(BeerDtoV2 beerDto) {
        return BeerDtoV2.builder()
                .id(UUID.randomUUID())
                .style(Style.PALE_ALE)
                .build();
    }

    @Override
    public void updateBeer(UUID id, BeerDtoV2 beerDto) {
        log.debug("Updating a beer.... id={}", id);
    }

    @Override
    public void deleteBeer(UUID id) {
        log.debug("Deleting a beer.... id={}", id);
    }
}
