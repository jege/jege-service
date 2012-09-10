package org.jege.abs.api;

import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

public abstract class AbstractApi {
    @Context
    protected UriInfo uriInfo;
    
    @OPTIONS
    public Response getOptions() {
        return getOptionsResponse();
    }
    
    @OPTIONS
    @Path("{url}")
    public Response getOptions(@PathParam("url") String url) {
        return getOptionsResponse();
    }
    
    private Response getOptionsResponse() {
        return Response.ok()
            .header("Content-Length", "0")
            .header("Content-Type", "text/plain")
            .build();
    }
}
