package com.dzinevich.brewery.web.controller.v2;

import com.dzinevich.brewery.services.v2.BeerServiceV2;
import com.dzinevich.brewery.web.model.v2.BeerDtoV2;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/v2/beer")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BeerControllerV2 {

    private final BeerServiceV2 beerServiceV2;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDtoV2> getBeer(@PathVariable("beerId") UUID id) {
        return new ResponseEntity<>(beerServiceV2.getBeerById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addBeer(@Validated @RequestBody BeerDtoV2 beerDto) {
        BeerDtoV2 savedDto = beerServiceV2.addNewBeer(beerDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("location", "/beer/" + savedDto.getId().toString());

        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<Void> updateBeer(@PathVariable("beerId") UUID id,
                                           @Validated @RequestBody BeerDtoV2 beerDto) {
        beerServiceV2.updateBeer(id, beerDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable("beerId") UUID id) {
        beerServiceV2.deleteBeer(id);
    }
}
