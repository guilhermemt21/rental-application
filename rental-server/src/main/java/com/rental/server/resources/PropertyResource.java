package com.rental.server.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import com.rental.server.api.PaginatedProperties;
import com.rental.server.core.property.Property;
import com.rental.server.core.property.PropertyService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

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
    public PaginatedProperties listProperties(@QueryParam("count") Integer count,
                                              @QueryParam("start") Integer start,
                                              @QueryParam("minLatitude") Double minLatitude,
                                              @QueryParam("maxLatitude") Double maxLatitude,
                                              @QueryParam("minLongitude") Double minLongitude,
                                              @QueryParam("maxLongitude") Double maxLongitude) {
        return propertyService.listPaginatedProperties(count, start, minLatitude, maxLatitude, minLongitude, maxLongitude);
    }
/*

    @GET
    @Timed
    @Path("/{projectId}/summary")
    public ProjectSummary getProjectSummary(@PathParam("projectId") Long projectId) {
        return projectService.getProjectSummary(projectId);
    }
*/

}
