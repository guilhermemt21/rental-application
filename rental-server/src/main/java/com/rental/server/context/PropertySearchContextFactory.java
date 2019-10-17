package com.rental.server.context;

import org.glassfish.jersey.server.internal.inject.AbstractContainerRequestValueFactory;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;


public final class PropertySearchContextFactory extends AbstractContainerRequestValueFactory<PropertySearch> {

    private final HttpServletRequest request;

    @Inject
    public PropertySearchContextFactory(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public PropertySearch provide() {
        return PropertySearch.builder()
                .minLatitude(Double.valueOf(request.getParameter("minLatitude")))
                .maxLatitude(Double.valueOf(request.getParameter("maxLatitude")))
                .minLongitude(Double.valueOf(request.getParameter("minLongitude")))
                .maxLongitude(Double.valueOf(request.getParameter("maxLongitude")))
                .minPrice(getParameterOrDefault(request.getParameter("minPrice"), Double.MIN_VALUE))
                .maxPrice(getParameterOrDefault(request.getParameter("maxPrice"), Double.MAX_VALUE))
                .minSize(getParameterOrDefault(request.getParameter("minSize"), Integer.MIN_VALUE))
                .maxSize(getParameterOrDefault(request.getParameter("maxSize"), Integer.MAX_VALUE))
                .bedroomsCount(getParameterOrDefault(request.getParameter("bedroomsCount"), Integer.MIN_VALUE))
                .build();
    }

    private Double getParameterOrDefault(String parameter, Double defaultValue) {
        if (parameter == null) {
            return defaultValue;
        }

        return Double.valueOf(parameter);
    }

    private Integer getParameterOrDefault(String parameter, Integer defaultValue) {
        if (parameter == null) {
            return defaultValue;
        }

        return Integer.valueOf(parameter);
    }


}
