package com.dzinevich.brewery.web.model;

import com.dzinevich.brewery.web.model.Style;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BeerDtoV2 implements Serializable {
    private static final long serialVersionUID = 4540009248496159729L;
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
