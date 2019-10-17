package com.rental.server.resources;

import com.rental.server.api.PaginatedProperties;
import com.rental.server.context.PropertySearch;
import com.rental.server.core.property.PropertyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class PropertyResourceTest {

    private PropertyResource propertyResource;

    @Mock
    private PropertyService propertyService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        propertyResource = new PropertyResource(propertyService);
    }

    @Test
    void shouldCallServiceOnListProperties() {
        int count = 10;
        int start = 20;
        PropertySearch propertySearch = PropertySearch.builder()
                .minLatitude(Double.MIN_VALUE)
                .maxLatitude(Double.MAX_VALUE)
                .build();
        PaginatedProperties expectedResponse = PaginatedProperties.builder().build();
        when(propertyService.listPaginatedProperties(count, start, propertySearch)).thenReturn(expectedResponse);

        assertEquals(expectedResponse, propertyResource.listProperties(count, start, propertySearch));
    }

}