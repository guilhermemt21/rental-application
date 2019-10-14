package com.rental.server.core.property;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface PropertyDAO {

    @SqlQuery("SELECT * FROM property " +
            " WHERE latitude >= :minLatitude AND latitude <= :maxLatitude AND " +
            "   longitude >= :minLongitude AND longitude <= :maxLongitude ")
    @RegisterRowMapper(PropertyMapper.class)
    List<Property> findPaginated(@Bind("offset") Integer offset,
                                 @Bind("limit") Integer limit,
                                 @Bind("minLatitude") Double minLatitude,
                                 @Bind("maxLatitude") Double maxLatitude,
                                 @Bind("minLongitude") Double minLongitude,
                                 @Bind("maxLongitude") Double maxLongitude);

    @SqlQuery("SELECT COUNT(*) FROM property")
    Long propertiesCount();

}
