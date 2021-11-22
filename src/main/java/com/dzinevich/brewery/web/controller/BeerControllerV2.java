package com.dzinevich.brewery.web.controller;

import com.dzinevich.brewery.services.BeerServiceV2;
import com.dzinevich.brewery.web.model.BeerPageableList;
import com.dzinevich.brewery.web.model.Style;
import com.dzinevich.brewery.web.model.BeerDtoV2;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RequestMapping("/v2/")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BeerControllerV2 {

    private final BeerServiceV2 beerServiceV2;

    @GetMapping(path = "/beer", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BeerPageableList> listBeers(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                      @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                      @RequestParam(value = "beerName", required = false) String name,
                                                      @RequestParam(value = "beerStyle", required = false) Style style,
                                                      @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand) {
        showInventoryOnHand = Optional.ofNullable(showInventoryOnHand).orElse(false);
        pageNumber = Optional.ofNullable(pageNumber).filter(nr -> nr > 0).orElse(0);
        pageSize = Optional.ofNullable(pageSize).filter(size -> size > 1).orElse(25);

        BeerPageableList beerList = beerServiceV2.listBeers(name, style, PageRequest.of(pageNumber, pageSize), showInventoryOnHand);

        return new ResponseEntity<>(beerList, HttpStatus.OK);
    }

    @GetMapping("/beer/{beerId}")
    public ResponseEntity<BeerDtoV2> getBeerById(@PathVariable("beerId") UUID id,
                                                 @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand) {
        showInventoryOnHand = Optional.ofNullable(showInventoryOnHand).orElse(false);
        return new ResponseEntity<>(beerServiceV2.getBeerById(id, showInventoryOnHand), HttpStatus.OK);
    }

    @GetMapping("/beerUpc/{upc}")
    public ResponseEntity<BeerDtoV2> getBeerByUpc(@PathVariable("upc") Long upc) {
        return new ResponseEntity<>(beerServiceV2.getBeerByUpc(upc), HttpStatus.OK);
    }

    @PostMapping("/beer")
    public ResponseEntity<BeerDtoV2> addBeer(@Validated @RequestBody BeerDtoV2 beerDto) {
        return new ResponseEntity<>(beerServiceV2.addNewBeer(beerDto), HttpStatus.CREATED);
    }

    @PutMapping("/beer/{beerId}")
    public ResponseEntity<BeerDtoV2> updateBeer(@PathVariable("beerId") UUID id,
                                                @Validated @RequestBody BeerDtoV2 beerDto) {
        return new ResponseEntity<>(beerServiceV2.updateBeer(id, beerDto), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/beer/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable("beerId") UUID id) {
        beerServiceV2.deleteBeer(id);
    }
}
