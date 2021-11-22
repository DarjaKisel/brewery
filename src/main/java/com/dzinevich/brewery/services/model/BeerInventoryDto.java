package com.dzinevich.brewery.services.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerInventoryDto {
    private UUID id;
    private OffsetDateTime createdOn;
    private OffsetDateTime modifiedOn;
    private UUID beerId;
    private Integer quantityOnHand;
}
