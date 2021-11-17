package com.dzinevich.brewery.web.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class BeerPageableList extends PageImpl<BeerDto> {
    public BeerPageableList(List<BeerDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public BeerPageableList(List<BeerDto> content) {
        super(content);
    }
}
