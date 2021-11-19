package com.dzinevich.brewery.services.v2.impl;

import com.dzinevich.brewery.domain.Beer;
import com.dzinevich.brewery.repositories.BeerRepository;
import com.dzinevich.brewery.services.v2.BeerServiceV2;
import com.dzinevich.brewery.web.exception.NotFoundException;
import com.dzinevich.brewery.web.mappers.BeerMapper;
import com.dzinevich.brewery.web.model.v2.BeerDtoV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BeerServiceV2Impl implements BeerServiceV2 {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDtoV2 getBeerById(UUID id) {
        return beerMapper.beerToBeerDto(
                beerRepository.findById(id).orElseThrow(NotFoundException::new)
        );
    }

    @Override
    public BeerDtoV2 addNewBeer(BeerDtoV2 beerDto) {
        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDtoV2 updateBeer(UUID id, BeerDtoV2 beerDto) {
        Beer beer = beerRepository.findById(id).orElseThrow(NotFoundException::new);

        beer.setBeerName(beerDto.getName());
        beer.setBeerStyle(beerDto.getStyle());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return beerMapper.beerToBeerDto(beerRepository.save(beer));
    }

    @Override
    public void deleteBeer(UUID id) {
        Beer beer = beerRepository.findById(id).orElseThrow(NotFoundException::new);
        beerRepository.delete(beer);
    }
}
