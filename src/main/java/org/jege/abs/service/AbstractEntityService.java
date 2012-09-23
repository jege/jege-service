package org.jege.abs.service;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import org.jege.abs.entity.AbstractEntity;
import org.jege.abs.entity.AbstractEntitySuppressible;

public abstract class AbstractEntityService<E extends AbstractEntity> extends AbstractService {
    private Class<E> entityClass;
    private String entityClassName;
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Class getEntityClass() {
        if(entityClass == null) {
            ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
            entityClass = (Class<E>) parameterizedType.getActualTypeArguments()[0];
        }
        return entityClass;
    }
    
    public String getEntityClassName() {
        if(entityClassName == null) {
            entityClassName = getEntityClass().toString().substring(getEntityClass().toString().lastIndexOf(".")+1);
        }
        return entityClassName;
    }
    
    @SuppressWarnings("unchecked")
    protected TypedQuery<E> createTypedQuery(String qlString) {
        return ((TypedQuery<E>) getPersistenceManager().createQuery(qlString, getEntityClass()));
    }
    
    protected TypedQuery<E> createTypedQuery(CriteriaQuery<E> criteriaQuery) {
        return ((TypedQuery<E>) getPersistenceManager().createQuery(criteriaQuery));
    }
    
    @SuppressWarnings("unchecked")
    protected TypedQuery<E> createNamedTypedQuery(String name) {
        return ((TypedQuery<E>) getPersistenceManager().createNamedQuery(name, getEntityClass()));
    }
    
    @SuppressWarnings("unchecked")
    public E find(String id) {
        return ((E) getPersistenceManager().find(getEntityClass(), id));
    }
    
    public List<E> findAll() {
        return createNamedTypedQuery(getEntityClassName() + AbstractEntity.FIND_ALL).getResultList();
    }
    
    public E findByIdName(String idName) {
        return createNamedTypedQuery(getEntityClassName() + AbstractEntity.FIND_BY_IDNAME).getSingleResult();
    }
    
    public void create(E entity) {
        getPersistenceManager().persist(entity);
    }
    
    public void update(E entity) {
        getPersistenceManager().merge(entity);
    }
    
    public void remove(Object entity) {
        if(entity instanceof AbstractEntitySuppressible) {
            AbstractEntitySuppressible suppressibleEntity = (AbstractEntitySuppressible) entity;
            suppressibleEntity.delete();
            update((E) suppressibleEntity);
        }
        else {
            getPersistenceManager().remove(entity);
        }
    }
    
    public void remove(String id) {
        remove(find(id));
    }
    
}
