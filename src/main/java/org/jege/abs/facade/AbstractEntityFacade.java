package org.jege.abs.facade;

import java.util.List;

import org.jege.abs.entity.AbstractEntity;
import org.jege.abs.service.AbstractEntityService;

public abstract class AbstractEntityFacade<E extends AbstractEntity> extends AbstractFacade {
    
    protected abstract AbstractEntityService<E> getService();
    
    @SuppressWarnings("rawtypes")
    public Class getEntityClass() {
        return getService().getEntityClass();
    }
    
    public String getEntityClassName() {
        return getService().getEntityClassName();
    }
    
    public List<E> findAll() {
        return getService().findAll();
    }
    
    public E find(String id) {
        return getService().find(id);
    }
    
    public void create(E entity) {
        getService().create(entity);
    }
    
    public void update(E entity) {
        getService().update(entity);
    }

}
