package com.rental.server.api;

import com.rental.server.core.property.Property;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PaginatedProperties {
    private Long propertiesCount;
    private List<Property> properties;
}
