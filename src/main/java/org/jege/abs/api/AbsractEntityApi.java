package org.jege.abs.api;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jege.abs.entity.AbstractEntity;
import org.jege.abs.facade.AbstractEntityFacade;
import org.jege.util.JsonMapper;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public abstract class AbsractEntityApi<E extends AbstractEntity> extends AbstractApi {
    protected abstract AbstractEntityFacade<E> getFacade();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<E> getAllEntities() {
        return getFacade().findAll();
    }
    
    @GET
    @Path("/{id:"+AbstractEntity.ID_PATTERN+"}")
    @Produces(MediaType.APPLICATION_JSON)
    public E getEntityById(@PathParam("id") String id) {
       return getFacade().find(id);
    }
    
    @PUT
    @Path("/{id:"+AbstractEntity.ID_PATTERN+"}")
    @Consumes(MediaType.APPLICATION_JSON)
    @SuppressWarnings("unchecked")
    public Response update(String entityJson) throws JsonParseException, JsonMappingException, IOException {
        E entity = (E) JsonMapper.jsonToObject(entityJson, getFacade().getEntityClass());
        getFacade().update(entity);
        return Response.ok().build();
    }
    
    @DELETE
    @Path("/{id:"+AbstractEntity.ID_PATTERN+"}")
    public void deleteEntityById(@PathParam("id") String id) {
       getFacade().remove(id);
    }
}
