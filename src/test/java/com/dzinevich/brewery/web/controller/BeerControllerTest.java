package com.dzinevich.brewery.web.controller;

import com.dzinevich.brewery.services.BeerService;
import com.dzinevich.brewery.web.model.BeerDto;
import com.dzinevich.brewery.web.model.Style;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest({BeerController.class})
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerService beerService;

    BeerDto newBeer;

    @BeforeEach
    void setUp() {
        newBeer = BeerDto.builder()
                .id(UUID.randomUUID())
                .name("BeerBeer")
                .style(Style.PORTER)
                .build();
    }

    @Test
    void getBeer() throws Exception {
        var beerId = UUID.randomUUID();
        given(beerService.getBeerById(beerId)).willReturn(newBeer);

        mockMvc.perform(get("/beer/" + beerId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(newBeer.getId().toString())))
                .andExpect(jsonPath("$.name", is(newBeer.getName())));
    }

    @Test
    void addBeer() throws Exception {
        String beerJson = objectMapper.writeValueAsString(newBeer);
        BeerDto savedBeer = BeerDto.builder().id(UUID.randomUUID()).name("SavedBeer").build();

        given(beerService.addNewBeer(newBeer)).willReturn(savedBeer);

        mockMvc.perform(post("/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerJson))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/beer/" + savedBeer.getId().toString()));
    }

    @Test
    void updateBeer() throws Exception {
        String beerJson = objectMapper.writeValueAsString(newBeer);

        doNothing().when(beerService).updateBeer(newBeer.getId(), newBeer);

        mockMvc.perform(put("/beer/" + newBeer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerJson))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteBeer() throws Exception {
        doNothing().when(beerService).deleteBeer(newBeer.getId());

        mockMvc.perform(delete("/beer/" + newBeer.getId()))
                .andExpect(status().isNoContent());
    }
}
