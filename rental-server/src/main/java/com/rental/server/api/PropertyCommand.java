package com.rental.server.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyCommand {
    private Long id;
    private String type;
    private String address;
    private String neighbourhood;
    private Integer size;
    private Integer bedrooms;
    private BigDecimal price;
}
