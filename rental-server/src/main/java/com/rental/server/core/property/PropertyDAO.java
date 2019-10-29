package com.rental.server.core.property;

import com.rental.server.api.PropertyCommand;
import com.rental.server.context.PropertySearch;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

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

    @SqlQuery("SELECT * FROM property ")
    @RegisterRowMapper(PropertyMapper.class)
    List<Property> findAll();

    @SqlQuery("SELECT COUNT(*) FROM property")
    Long propertiesCount();

    @SqlUpdate("INSERT INTO property (type, address, neighbourhood, latitude, longitude, price, size, bedrooms) " +
            " VALUES (:property.type, :property.address, :property.neighbourhood, 0, 0, :property.price, :property.size, :property.bedrooms)")
    void createProperty(@BindBean("property") PropertyCommand property);

    @SqlUpdate("UPDATE property " +
            " SET type = :property.type, " +
            " address = :property.address, " +
            " neighbourhood = :property.neighbourhood, " +
            " price = :property.price, " +
            " size = :property.size, " +
            " bedrooms = :property.bedrooms" +
            " WHERE id = :property.id ")
    void editProperty(@BindBean("property") PropertyCommand property);

    @SqlUpdate("DELETE FROM property WHERE id = :id")
    void deleteProperty(@Bind("id") Long id);

}
