package com.dzinevich.brewery.services.impl;

import com.dzinevich.brewery.services.BeerService;
import com.dzinevich.brewery.web.model.BeerDto;
import com.dzinevich.brewery.web.model.Style;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public BeerDto getBeerById(UUID id) {
        return BeerDto.builder()
                .id(UUID.randomUUID())
                .name("Angry IPA")
                .style(Style.IPA)
                .build();
    }

    @Override
    public BeerDto addNewBeer(BeerDto beerDto) {
        return BeerDto.builder()
                .id(UUID.randomUUID())
                .style(Style.PALE_ALE)
                .build();
    }

    @Override
    public void updateBeer(UUID id, BeerDto beerDto) {
        log.debug("Updating a beer.... id={}", id);
    }

    @Override
    public void deleteBeer(UUID id) {
        log.debug("Deleting a beer.... id={}", id);
    }
}
