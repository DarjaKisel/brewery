package com.dzinevich.brewery.web.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class BeerPageableList extends PageImpl<BeerDtoV2> implements Serializable {
    private static final long serialVersionUID = -2204206673028841204L;

    public BeerPageableList(List<BeerDtoV2> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public BeerPageableList(List<BeerDtoV2> content) {
        super(content);
    }
}
