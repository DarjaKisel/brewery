package com.dzinevich.brewery.repositories;

import com.dzinevich.brewery.domain.Beer;
import com.dzinevich.brewery.web.model.Style;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
    Page<Beer> findAllByBeerName(String name, Pageable pageable);
    Page<Beer> findAllByBeerStyle(Style style, Pageable pageable);
    Page<Beer> findAllByBeerNameAndBeerStyle(String name, Style style, Pageable pageable);
    Optional<Beer> findByUpc(Long upc);
}
