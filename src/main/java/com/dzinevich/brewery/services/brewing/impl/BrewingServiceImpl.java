package com.dzinevich.brewery.services.brewing.impl;

import com.dzinevich.brewery.config.JmsConfig;
import com.dzinevich.brewery.events.BrewBeerEvent;
import com.dzinevich.brewery.repositories.BeerRepository;
import com.dzinevich.brewery.services.BeerInventoryService;
import com.dzinevich.brewery.services.brewing.BrewingService;
import com.dzinevich.brewery.web.mappers.BeerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class BrewingServiceImpl implements BrewingService {
    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Override
    @Scheduled(fixedRate = 5000)
    public void checkForLowInventory() {
        beerRepository.findAll().stream()
                .filter(beer -> {
                    var onHandInventory = beerInventoryService.getOnHandInventory(beer.getUpc());
                    log.debug("Beer UPC={} has min on hand={}", beer.getUpc(), beer.getMinQtyOnHand());
                    log.debug("Inventory={}", onHandInventory);
                    return onHandInventory <= beer.getMinQtyOnHand();
                })
                .forEach(beer -> {
                    var beerDto = beerMapper.beerToBeerDto(beer);
                    jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST, new BrewBeerEvent(beerDto));
                });

    }

}
