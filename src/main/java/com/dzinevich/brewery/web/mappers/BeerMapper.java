package com.dzinevich.brewery.web.mappers;

import com.dzinevich.brewery.domain.Beer;
import com.dzinevich.brewery.web.model.v2.BeerDtoV2;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {DateMapper.class})
public interface BeerMapper {
    @Mapping(source = "beerId", target = "id")
    @Mapping(source = "beerName", target = "name")
    @Mapping(source = "beerStyle", target = "style")
    @Mapping(source = "modifiedOn", target = "lastModifiedOn")
    BeerDtoV2 beerToBeerDto(Beer beer);

    @Mapping(source = "id", target = "beerId")
    @Mapping(source = "name", target = "beerName")
    @Mapping(source = "style", target = "beerStyle")
    @Mapping(source = "lastModifiedOn", target = "modifiedOn")
    Beer beerDtoToBeer(BeerDtoV2 beerDto);
}
