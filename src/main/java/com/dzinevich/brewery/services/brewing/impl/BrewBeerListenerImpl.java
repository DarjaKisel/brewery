package com.dzinevich.brewery.services.brewing.impl;

import com.dzinevich.brewery.config.JmsConfig;
import com.dzinevich.brewery.domain.Beer;
import com.dzinevich.brewery.events.BrewBeerEvent;
import com.dzinevich.brewery.events.NewInventoryEvent;
import com.dzinevich.brewery.repositories.BeerRepository;
import com.dzinevich.brewery.services.brewing.BrewBeerListener;
import com.dzinevich.brewery.web.model.BeerDtoV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class BrewBeerListenerImpl implements BrewBeerListener {
    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    @Override
    @Transactional
    @JmsListener(destination = JmsConfig.BREWING_REQUEST)
    public void receiveBrewBeer(BrewBeerEvent brewBeerEvent) {
        BeerDtoV2 beerDto = brewBeerEvent.getBeerDto();
        Beer beer = beerRepository.getById(beerDto.getId());
        Integer qtyToBrew = beer.getQtyToBrew();
        beerDto.setQuantityOnHand(qtyToBrew);

        log.debug("Brewed beer={} with quantity on hand={}", beer.getMinQtyOnHand(), beerDto.getQuantityOnHand());

        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto);
        jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_REQUEST, newInventoryEvent);
    }
}
