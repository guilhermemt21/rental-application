package com.rental.server.context;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class PropertySearch {
    private Double minLatitude;
    private Double maxLatitude;
    private Double minLongitude;
    private Double maxLongitude;
    private Double minPrice;
    private Double maxPrice;
    private Integer minSize;
    private Integer maxSize;
    private Integer bedroomsCount;
}
