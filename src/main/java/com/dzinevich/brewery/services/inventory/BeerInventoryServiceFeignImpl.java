package com.dzinevich.brewery.services.inventory;

import com.dzinevich.brewery.services.inventory.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Profile("local-discovery")
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class BeerInventoryServiceFeignImpl implements BeerInventoryService {

    private final InventoryServiceClient inventoryServiceClient;

    @Override
    public Integer getOnHandInventory(Long beerUpc) {
        log.debug("Calling inventory service with upc={}", beerUpc);

        ResponseEntity<List<BeerInventoryDto>> responseEntity = inventoryServiceClient.getOnHandInventory(beerUpc);

        Integer onHand = Objects.requireNonNull(responseEntity.getBody())
                .stream()
                .mapToInt(BeerInventoryDto::getQuantityOnHand)
                .sum();

        log.debug("Beer upc={} on hand is={}", beerUpc, onHand);

        return onHand;
    }
}
