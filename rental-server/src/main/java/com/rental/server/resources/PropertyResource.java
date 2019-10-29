package com.rental.server.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import com.rental.server.api.PaginatedProperties;
import com.rental.server.api.PropertyCommand;
import com.rental.server.context.PropertySearch;
import com.rental.server.core.property.PropertyService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/property")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
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

    @GET
    @Timed
    @Path("/all")
    public List<PropertyCommand> allProperties() {
        return propertyService.listAllProperties().stream().map(p -> new PropertyCommand(p.getId(), p.getType(), p.getAddress(), p.getNeighbourhood(), p.getSize(), p.getBedrooms(), p.getPrice())).collect(Collectors.toList());
    }

    @POST
    @Timed
    @Path("/create")
    public void createProperty(PropertyCommand property) {
        propertyService.createProperty(property);
    }

    @PUT
    @Timed
    @Path("/edit")
    public void editProperty(PropertyCommand property) {
        propertyService.editProperty(property);
    }

    @DELETE
    @Timed
    @Path("/delete/{id}")
    public void deleteProperty(@PathParam("id") Long id) {
        propertyService.deleteProperty(id);
    }

}
