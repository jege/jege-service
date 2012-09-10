package org.jege.util.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import org.jege.abs.api.AbstractApi;
import org.jege.user.user.UserFacade;
import org.jege.util.JsonMapper;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Path("")
public class GlobalApi extends AbstractApi {
    @Inject
    private UserFacade userFacade;
    
    @POST
    @Path("/signin")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response signin(String credentialsJson) throws JsonGenerationException, JsonMappingException, IOException {
        Map<String, Object> credentials = null;
        
        try {
            credentials = JsonMapper.jsonToMap(credentialsJson);
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        String username = credentials.get("username").toString();
        String password = credentials.get("password").toString();
        
        String id = userFacade.signIn(username, password);
        
        if(id != null) {
            Map<String, String> responseBody = new HashMap<String, String>();
            responseBody.put("userid", id);
            NewCookie useridCookie = new NewCookie("userid", id);
            return Response.ok(JsonMapper.objectToJson(responseBody), MediaType.APPLICATION_JSON).cookie(useridCookie).build();
        } else {
            return Response.status(401).build();
        }
    }
}
