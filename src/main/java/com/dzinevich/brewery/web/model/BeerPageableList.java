package com.dzinevich.brewery.web.model;

import com.dzinevich.brewery.web.model.v2.BeerDtoV2;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class BeerPageableList extends PageImpl<BeerDtoV2> {
    public BeerPageableList(List<BeerDtoV2> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public BeerPageableList(List<BeerDtoV2> content) {
        super(content);
    }
}
