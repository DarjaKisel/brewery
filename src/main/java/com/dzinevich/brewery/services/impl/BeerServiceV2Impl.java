package com.dzinevich.brewery.services.impl;

import com.dzinevich.brewery.domain.Beer;
import com.dzinevich.brewery.repositories.BeerRepository;
import com.dzinevich.brewery.services.BeerServiceV2;
import com.dzinevich.brewery.web.exception.NotFoundException;
import com.dzinevich.brewery.web.mappers.BeerMapper;
import com.dzinevich.brewery.web.model.BeerPageableList;
import com.dzinevich.brewery.web.model.Style;
import com.dzinevich.brewery.web.model.BeerDtoV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BeerServiceV2Impl implements BeerServiceV2 {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Cacheable(
            cacheNames = "beerListCache",
            condition = "#showInventoryOnHand==false"
    )
    @Override
    public BeerPageableList listBeers(String name,
                                      Style style,
                                      PageRequest pageRequest,
                                      boolean showInventoryOnHand) {
        Page<Beer> beerPage;

        if (StringUtils.isNotBlank(name) && style != null) {
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(name, style, pageRequest);
        } else if (StringUtils.isNotBlank(name)) {
            beerPage = beerRepository.findAllByBeerName(name, pageRequest);
        } else if (style != null) {
            beerPage = beerRepository.findAllByBeerStyle(style, pageRequest);
        } else {
            beerPage = beerRepository.findAll(pageRequest);
        }

        List<BeerDtoV2> content = showInventoryOnHand
                ? beerPage.getContent().stream().map(beerMapper::beerToBeerDtoWithInventory).collect(Collectors.toList())
                : beerPage.getContent().stream().map(beerMapper::beerToBeerDto).collect(Collectors.toList());

        return new BeerPageableList(content,
                PageRequest.of(beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize()),
                beerPage.getTotalElements());
    }

    @Cacheable(
            cacheNames = "beerCache",
            key = "#id",
            condition = "#showInventoryOnHand==false"
    )
    @Override
    public BeerDtoV2 getBeerById(UUID id, boolean showInventoryOnHand) {
        Beer beer = beerRepository.findById(id).orElseThrow(NotFoundException::new);

        return showInventoryOnHand
                ? beerMapper.beerToBeerDtoWithInventory(beer)
                : beerMapper.beerToBeerDto(beer);
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
