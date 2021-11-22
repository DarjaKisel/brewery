package com.dzinevich.brewery.web.mappers;

import com.dzinevich.brewery.domain.Beer;
import com.dzinevich.brewery.web.model.BeerDtoV2;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DateMapper.class},
        componentModel="spring")
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {
    @Mapping(source = "beerId", target = "id")
    @Mapping(source = "beerName", target = "name")
    @Mapping(source = "beerStyle", target = "style")
    @Mapping(source = "modifiedOn", target = "lastModifiedOn")
    @Mapping(source = "minQtyOnHand", target = "quantityOnHand")
    BeerDtoV2 beerToBeerDto(Beer beer);

    @Mapping(source = "beerId", target = "id")
    @Mapping(source = "beerName", target = "name")
    @Mapping(source = "beerStyle", target = "style")
    @Mapping(source = "modifiedOn", target = "lastModifiedOn")
    @Mapping(source = "minQtyOnHand", target = "quantityOnHand")
    BeerDtoV2 beerToBeerDtoWithInventory(Beer beer);

    @Mapping(source = "id", target = "beerId")
    @Mapping(source = "name", target = "beerName")
    @Mapping(source = "style", target = "beerStyle")
    @Mapping(source = "lastModifiedOn", target = "modifiedOn")
    @Mapping(source = "quantityOnHand", target = "minQtyOnHand")
    Beer beerDtoToBeer(BeerDtoV2 beerDto);
}
