package com.rental.server.core.property;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class Property {
    private Long id;
    private Double latitude;
    private Double longitude;
    private String type;
    private String address;
    private String neighbourhood;
    private Integer size;
    private Integer bedrooms;
    private BigDecimal price;
    private PropertyUrls urls;
}
