package com.rental.server.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import com.rental.server.api.PaginatedProperties;
import com.rental.server.context.PropertySearch;
import com.rental.server.core.property.PropertyService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/property")
@Produces(MediaType.APPLICATION_JSON)
public class PropertyResource {

    private final PropertyService propertyService;

    @Inject
    public PropertyResource(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GET
    @Timed
    @Path("/list")
    public PaginatedProperties listProperties(@QueryParam("count") Integer count, @QueryParam("start") Integer start, @Context PropertySearch propertySearch) {
        return propertyService.listPaginatedProperties(count, start, propertySearch);
    }

}
