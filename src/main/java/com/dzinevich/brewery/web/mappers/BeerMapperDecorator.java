package com.dzinevich.brewery.web.mappers;

import com.dzinevich.brewery.domain.Beer;
import com.dzinevich.brewery.services.BeerInventoryService;
import com.dzinevich.brewery.web.model.BeerDtoV2;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@NoArgsConstructor
public abstract class BeerMapperDecorator implements BeerMapper {

    @Autowired
    private BeerInventoryService beerInventoryService;

    @Autowired
    @Qualifier("delegate")
    private BeerMapper beerMapper;

    @Override
    public BeerDtoV2 beerToBeerDto(Beer beer) {
        return beerMapper.beerToBeerDto(beer);
    }

    @Override
    public BeerDtoV2 beerToBeerDtoWithInventory(Beer beer) {
        BeerDtoV2 beerDto = beerMapper.beerToBeerDto(beer);
        beerDto.setQuantityOnHand(beerInventoryService.getOnHandInventory(beer.getUpc()));
        return beerDto;
    }

    @Override
    public Beer beerDtoToBeer(BeerDtoV2 beerDto) {
        return beerMapper.beerDtoToBeer(beerDto);
    }
}
