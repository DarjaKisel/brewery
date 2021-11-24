package com.dzinevich.brewery.services.impl;

import com.dzinevich.brewery.services.BeerInventoryService;
import com.dzinevich.brewery.services.model.BeerInventoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class BeerInventoryServiceRestTemplateImpl implements BeerInventoryService {

    private static final String INVENTORY_PATH = "/beer/{beerId}/inventory";
    private final RestTemplate restTemplate;
    private final String inventoryHostName;

    public BeerInventoryServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilder,
                                                @Value("${beer.inventory-hostname}") String inventoryHostName) {
        this.restTemplate = restTemplateBuilder.build();
        this.inventoryHostName = inventoryHostName;
    }

    @Override
    public Integer getOnHandInventory(Long beerUpc) {
        log.debug("Calling inventory service....");

        ResponseEntity<List<BeerInventoryDto>> responseEntity = restTemplate
                .exchange(inventoryHostName + INVENTORY_PATH, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<BeerInventoryDto>>() {}, String.valueOf(beerUpc));

        return Objects.requireNonNull(responseEntity.getBody())
                .stream()
                .mapToInt(BeerInventoryDto::getQuantityOnHand)
                .sum();
    }
}
