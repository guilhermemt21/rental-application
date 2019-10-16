package com.rental.server.core.property;

import com.google.inject.Inject;
import com.rental.server.api.PaginatedProperties;

import javax.ws.rs.QueryParam;
import java.util.List;

public class PropertyService {


    private PropertyDAO propertyDAO;

    @Inject
    public PropertyService(PropertyDAO propertyDAO) {
        this.propertyDAO = propertyDAO;
    }


    public PaginatedProperties listPaginatedProperties(Integer count, Integer start, Double minLatitude, Double maxLatitude, Double minLongitude, Double maxLongitude,
                                                       Double minPrice, Double maxPrice, Integer minSize, Integer maxSize, Integer bedroomsCount) {
        Long propertiesCount = propertyDAO.propertiesCount();
        List<Property> paginatedProperties = propertyDAO.findPaginated(start, count, minLatitude, maxLatitude, minLongitude, maxLongitude,
                minPrice == null ? 0 : minPrice, maxPrice == null ? Double.MAX_VALUE : maxPrice, minSize == null ? 0 : minSize, maxSize == null ? Integer.MAX_VALUE : maxSize, bedroomsCount == null ? 0 : bedroomsCount);
        return PaginatedProperties.builder().propertiesCount(propertiesCount).properties(paginatedProperties).build();
    }


}
