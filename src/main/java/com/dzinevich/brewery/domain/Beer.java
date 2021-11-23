package com.dzinevich.brewery.domain;

import com.dzinevich.brewery.web.model.Style;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Beer {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID beerId;
    @Version
    private Long version;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdOn;
    @UpdateTimestamp
    private Timestamp modifiedOn;

    private String beerName;
    @Enumerated(EnumType.STRING)
    private Style beerStyle;

    @Column(unique = true)
    private Long upc;
    private BigDecimal price;

    private Integer minQtyOnHand;
    private Integer qtyToBrew;
}
