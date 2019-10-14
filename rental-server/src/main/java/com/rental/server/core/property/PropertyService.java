package com.rental.server.core.property;

import com.google.inject.Inject;
import com.rental.server.api.PaginatedProperties;

import java.util.List;

public class PropertyService {


    private PropertyDAO propertyDAO;

    @Inject
    public PropertyService(PropertyDAO propertyDAO) {
        this.propertyDAO = propertyDAO;
    }


    public PaginatedProperties listPaginatedProperties(Integer count, Integer start, Double minLatitude, Double maxLatitude, Double minLongitude, Double maxLongitude) {
        Long propertiesCount = propertyDAO.propertiesCount();
        List<Property> paginatedProperties = propertyDAO.findPaginated(start, count, minLatitude, maxLatitude, minLongitude, maxLongitude);
        return PaginatedProperties.builder().propertiesCount(propertiesCount).properties(paginatedProperties).build();
    }


}
