package com.rental.server.core.property;

import com.google.inject.Inject;
import com.rental.server.api.PaginatedProperties;
import com.rental.server.context.PropertySearch;

import java.util.List;

public class PropertyService {


    private PropertyDAO propertyDAO;

    @Inject
    public PropertyService(PropertyDAO propertyDAO) {
        this.propertyDAO = propertyDAO;
    }


    public PaginatedProperties listPaginatedProperties(Integer count, Integer start, PropertySearch propertySearch) {
        Long propertiesCount = propertyDAO.propertiesCount();
        List<Property> paginatedProperties = propertyDAO.findPaginated(start, count, propertySearch);
        return PaginatedProperties.builder().propertiesCount(propertiesCount).properties(paginatedProperties).build();
    }


}
