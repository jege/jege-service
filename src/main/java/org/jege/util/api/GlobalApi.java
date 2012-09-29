package org.jege.util.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import org.jege.abs.api.AbstractApi;
import org.jege.user.user.UserFacade;
import org.jege.util.JsonMapper;
import org.jege.util.time.TimeUserType;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Path("")
public class GlobalApi extends AbstractApi {
    @GET
    @Path("/test")
    public void test() {
        System.out.println(TimeUserType.LOCAL_DATE);
        System.out.println(TimeUserType.PERIOD);
    }
}
