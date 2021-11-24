package com.dzinevich.brewery.events;

import com.dzinevich.brewery.web.model.BeerDtoV2;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerEvent implements Serializable {
    private static final long serialVersionUID = -1962266684437409500L;

    private BeerDtoV2 beerDto;
}
