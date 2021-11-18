package com.dzinevich.brewery.web.model.v2;

import com.dzinevich.brewery.web.model.Style;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDtoV2 {
    @Null
    private UUID id;
    @Null
    private Integer version;

    @Null
    private OffsetDateTime createdOn;
    @Null
    private OffsetDateTime lastModifiedOn;

    @NotBlank
    private String name;
    @NotNull
    private Style style;

    @Positive
    @NotNull
    private Long upc;
    @Positive
    @NotNull
    private BigDecimal price;
    private Integer quantityOnHand;
}
