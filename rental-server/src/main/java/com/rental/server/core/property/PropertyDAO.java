package com.rental.server.core.property;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface PropertyDAO {

    @SqlQuery("SELECT * FROM property " +
            " WHERE " +
            "   latitude >= :minLatitude AND latitude <= :maxLatitude AND " +
            "   longitude >= :minLongitude AND longitude <= :maxLongitude AND " +
            "   price >= :minPrice AND price <= :maxPrice AND" +
            "   size >= :minSize AND size <= :maxSize AND" +
            "   bedrooms >= :bedroomsCount")
    @RegisterRowMapper(PropertyMapper.class)
    List<Property> findPaginated(@Bind("offset") Integer offset,
                                 @Bind("limit") Integer limit,
                                 @Bind("minLatitude") Double minLatitude,
                                 @Bind("maxLatitude") Double maxLatitude,
                                 @Bind("minLongitude") Double minLongitude,
                                 @Bind("maxLongitude") Double maxLongitude,
                                 @Bind("minPrice") Double minPrice,
                                 @Bind("maxPrice") Double maxPrice,
                                 @Bind("minSize") Integer minSize,
                                 @Bind("maxSize") Integer maxSize,
                                 @Bind("bedroomsCount") Integer bedroomsCount);

    @SqlQuery("SELECT COUNT(*) FROM property")
    Long propertiesCount();

}
