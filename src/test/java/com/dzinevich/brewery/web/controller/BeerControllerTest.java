package com.dzinevich.brewery.web.controller;

import com.dzinevich.brewery.services.BeerServiceV2;
import com.dzinevich.brewery.web.model.BeerDtoV2;
import com.dzinevich.brewery.web.model.Style;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest({BeerControllerV2.class})
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerServiceV2 beerServiceV2;

    BeerDtoV2 newBeer;

    @BeforeEach
    void setUp() {
        newBeer = BeerDtoV2.builder()
                .name("BeerBeer")
                .style(Style.PORTER)
                .upc(123000000000000067L)
                .price(BigDecimal.valueOf(11.99))
                .build();
    }

    @Test
    void getBeer() throws Exception {
        var beerId = UUID.randomUUID();
        given(beerServiceV2.getBeerById(beerId, false)).willReturn(newBeer);

        mockMvc.perform(get("/v2/beer/" + beerId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.upc", is(newBeer.getUpc())))
                .andExpect(jsonPath("$.name", is(newBeer.getName())));
    }

    @Test
    void addBeer() throws Exception {
        String beerJson = objectMapper.writeValueAsString(newBeer);

        given(beerServiceV2.addNewBeer(newBeer)).willReturn(newBeer);

        mockMvc.perform(post("/v2/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(newBeer.getName())))
                .andExpect(jsonPath("$.upc", is(newBeer.getUpc())))
                .andExpect(jsonPath("$.style", is(newBeer.getStyle().name())));
    }

    @Test
    void updateBeer() throws Exception {
        String beerJson = objectMapper.writeValueAsString(newBeer);

        given(beerServiceV2.updateBeer(any(UUID.class), eq(newBeer))).willReturn(newBeer);

        mockMvc.perform(put("/v2/beer/" + UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerJson))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.name", is(newBeer.getName())))
                .andExpect(jsonPath("$.upc", is(newBeer.getUpc())))
                .andExpect(jsonPath("$.style", is(newBeer.getStyle().name())));
    }

    @Test
    void deleteBeer() throws Exception {
        var beerId = UUID.randomUUID();
        doNothing().when(beerServiceV2).deleteBeer(beerId);

        mockMvc.perform(delete("/v2/beer/" + beerId))
                .andExpect(status().isNoContent());
    }
}
