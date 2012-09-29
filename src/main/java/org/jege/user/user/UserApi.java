package org.jege.user.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jege.abs.api.AbsractEntityApi;
import org.jege.util.JsonMapper;
import org.jege.util.PersonaHelper;

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

        User loggedInUser = getFacade().signIn(username, password);

        if (loggedInUser != null) {
            messages.success("Success");
            return Response.ok(getSuccessSigninResponseBody(loggedInUser), MediaType.APPLICATION_JSON).build();
        } else {
            messages.error("Error");
            return Response.status(401).build();
        }
    }

    @POST
    @Path("/verify")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response verify(String assertion) {
        // TODO get them from DB or conf
        String audience = "http://localhost:4242";
        String issuer = "https://verifier.login.persona.org/verify";
        try {
            // Send the request
            URL url = new URL(issuer);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

            // Write parameters
            writer.write("assertion="+assertion+"&audience="+audience);
            writer.flush();

            // Get the response
            StringBuffer answer = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                answer.append(line);
            }
            writer.close();
            reader.close();

            // Output the response
            Map<String, Object> response = JsonMapper.jsonToMap(answer.toString());
            
            if(PersonaHelper.VerifyStatus.OKAY.getName().equals(
                    response.get(PersonaHelper.VerifyParam.STATUS.getName()))) {
                User loggedInUser = getFacade().findByEmail(
                        response.get(PersonaHelper.VerifyParam.EMAIL.getName()).toString());
                messages.success("Successfuly logged in!");
                return Response.ok(getSuccessSigninResponseBody(loggedInUser), MediaType.APPLICATION_JSON).build();
            } else {
                messages.error(response.get(PersonaHelper.VerifyParam.REASON.getName()).toString());
                return Response.status(Status.UNAUTHORIZED).build();
            }
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return Response.status(Status.UNAUTHORIZED).build();
    }
    
    private String getSuccessSigninResponseBody(User loggedInUser) throws JsonGenerationException, JsonMappingException, IOException {
        Map<String, String> responseBody = new HashMap<String, String>();
        responseBody.put("id", loggedInUser.getId());
        responseBody.put("email", loggedInUser.getEmail());
        return JsonMapper.objectToJson(responseBody);
    }
}
