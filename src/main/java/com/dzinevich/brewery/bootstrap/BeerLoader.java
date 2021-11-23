package com.dzinevich.brewery.bootstrap;

import com.dzinevich.brewery.domain.Beer;
import com.dzinevich.brewery.repositories.BeerRepository;
import com.dzinevich.brewery.web.model.Style;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BeerLoader implements CommandLineRunner {
    private static final long UPC_1 = 17500000000038L;
    private static final long UPC_2 = 98100000000038L;
    private static final long UPC_3 = 82100000000014L;
    private static final UUID UUID_1 = UUID.fromString("0ee1d5f6-27d2-46c3-b815-68006d4c1a64");
    private static final UUID UUID_2 = UUID.fromString("29f700c5-515d-4682-818d-030202934f70");
    private static final UUID UUID_3 = UUID.fromString("9d80db29-dd99-45b1-9754-a44040d16c06");

    private final BeerRepository beerRepository;

    @Override
    public void run(String... args) {
        loadTheBeer();
    }

    private void loadTheBeer() {
        if (beerRepository.count() == 0) {
            beerRepository.save(
                    Beer.builder()
                            .beerId(UUID_1)
                            .beerName("Virmalised IPA")
                            .beerStyle(Style.IPA)
                            .price(BigDecimal.valueOf(2.10))
                            .upc(UPC_1)
                            .qtyToBrew(200)
                            .build()
            );
            beerRepository.save(
                    Beer.builder()
                            .beerId(UUID_2)
                            .beerName("Marmelade")
                            .beerStyle(Style.GOSE)
                            .price(BigDecimal.valueOf(4.45))
                            .upc(UPC_2)
                            .qtyToBrew(50)
                            .build()
            );
            beerRepository.save(
                    Beer.builder()
                            .beerId(UUID_3)
                            .beerName("Cat's Favourite")
                            .beerStyle(Style.SAISON)
                            .price(BigDecimal.valueOf(4.10))
                            .upc(UPC_3)
                            .qtyToBrew(80)
                            .build()
            );
        }
    }
}
