package org.jege.user.user;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jege.abs.api.AbsractEntityApi;
import org.jege.util.JsonMapper;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Path(UserApi.PATH)
@RequestScoped
public class UserApi extends AbsractEntityApi<User> {
    public static final String PATH = "/users";
    
    @Inject
    private UserFacade userFacade;
    
    @Override
    protected UserFacade getFacade() {
        return userFacade;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String userJson) {
        User user = null;
        Map<String, Object> mapUser = null;
        
        try {
            user = JsonMapper.jsonToObject(userJson, User.class);
            mapUser = JsonMapper.jsonToMap(userJson);
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
        
        getFacade().create(user);
        
        URI userUri = uriInfo.getAbsolutePathBuilder().path(user.getId().toString()).build();
        
        return Response.created(userUri).build();
    }
    
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
        
        String id = getFacade().signIn(username, password);
        
        if(id != null) {
            Map<String, String> responseBody = new HashMap<String, String>();
            responseBody.put("userid", id);
            return Response.ok(JsonMapper.objectToJson(responseBody), MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(401).build();
        }
    }
}
