package org.jege.abs.entity;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class AbstractEntitySuppressible extends AbstractEntity {
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDeleted;
    
    public void delete() {
        dateDeleted = new Date();
    }
    
    public void undelete() {
        dateDeleted = null;
    }

    public boolean isDeleted() {
        return dateDeleted != null;
    }
}
