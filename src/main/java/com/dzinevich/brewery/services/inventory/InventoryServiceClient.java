package com.dzinevich.brewery.services.inventory;

import com.dzinevich.brewery.services.inventory.model.BeerInventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "beer-inventory")
@Component
public interface InventoryServiceClient {
    String INVENTORY_PATH = "/beer/{beerUpc}/inventory";

    @RequestMapping(method = RequestMethod.GET, value = INVENTORY_PATH)
    ResponseEntity<List<BeerInventoryDto>> getOnHandInventory(@PathVariable("beerUpc") Long beerUpc);
}
