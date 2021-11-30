package com.dzinevich.brewery.services.inventory.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class BeerInventoryDto {
    private UUID id;
    @JsonProperty(value = "createdDate")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss:Z", shape = JsonFormat.Shape.STRING)
    private OffsetDateTime createdOn;
    @JsonProperty(value = "lastModifiedDate")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss:Z", shape = JsonFormat.Shape.STRING)
    private OffsetDateTime modifiedOn;
    private UUID beerId;
    private Integer quantityOnHand;
}
