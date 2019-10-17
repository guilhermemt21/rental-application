package com.rental.server.core.property;

import com.rental.server.context.PropertySearch;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface PropertyDAO {

    @SqlQuery("SELECT * FROM property " +
            " WHERE " +
            "   latitude >= :propertySearch.minLatitude AND latitude <= :propertySearch.maxLatitude AND " +
            "   longitude >= :propertySearch.minLongitude AND longitude <= :propertySearch.maxLongitude AND " +
            "   price >= :propertySearch.minPrice AND price <= :propertySearch.maxPrice AND" +
            "   size >= :propertySearch.minSize AND size <= :propertySearch.maxSize AND" +
            "   bedrooms >= :propertySearch.bedroomsCount")
    @RegisterRowMapper(PropertyMapper.class)
    List<Property> findPaginated(@Bind("offset") Integer offset, @Bind("limit") Integer limit,
                                 @BindBean("propertySearch") PropertySearch propertySearch);

    @SqlQuery("SELECT COUNT(*) FROM property")
    Long propertiesCount();

}
