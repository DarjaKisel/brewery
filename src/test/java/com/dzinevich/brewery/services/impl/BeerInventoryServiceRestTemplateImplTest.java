package com.dzinevich.brewery.services.impl;

import com.dzinevich.brewery.services.inventory.BeerInventoryService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled("Utility for manual testing")
@SpringBootTest
class BeerInventoryServiceRestTemplateImplTest {

    @Autowired
    BeerInventoryService beerInventoryService;

    @Test
    void getOnHandInventory() {
        int onHandInventory = beerInventoryService
                .getOnHandInventory(98100000000038L);
        System.out.println(onHandInventory);
    }
}