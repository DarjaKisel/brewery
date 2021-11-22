package com.dzinevich.brewery.bootstrap;

import com.dzinevich.brewery.domain.Beer;
import com.dzinevich.brewery.repositories.BeerRepository;
import com.dzinevich.brewery.web.model.Style;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BeerLoader implements CommandLineRunner {
    private static final long UPC_1 = 17500000000038L;
    private static final long UPC_2 = 98100000000038L;

    private final BeerRepository beerRepository;

    @Override
    public void run(String... args) {
        loadTheBeer();
    }

    private void loadTheBeer() {
        if (beerRepository.count() == 0) {
            beerRepository.save(
                    Beer.builder()
                            .beerName("Virmalised IPA")
                            .beerStyle(Style.IPA)
                            .price(BigDecimal.valueOf(2.10))
                            .minQtyOnHand(10)
                            .upc(UPC_1)
                            .qtyToBrew(200)
                            .build()
            );
            beerRepository.save(
                    Beer.builder()
                            .beerName("Marmelade")
                            .beerStyle(Style.GOSE)
                            .price(BigDecimal.valueOf(4.45))
                            .minQtyOnHand(5)
                            .upc(UPC_2)
                            .qtyToBrew(50)
                            .build()
            );
        }
    }
}
