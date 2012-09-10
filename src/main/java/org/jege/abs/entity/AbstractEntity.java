package org.jege.abs.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@MappedSuperclass
public abstract class AbstractEntity {
    public static final String ID_PATTERN = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}";
    public static final String FIND_ALL = ".findAll";
    public static final String FIND_BY_IDNAME = ".findByIdName";
    
    @Id
    private String id = UUID.randomUUID().toString();
    
    @Version
    private long version;
    
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;
    
    public boolean isManaged() {
        return dateCreated != null;
    }
    
    @PrePersist
    protected void onPersist() {
        dateCreated = new Date();
        dateUpdated = new Date();
    }
    
    @PreUpdate
    protected void onUpdate() {
        dateUpdated = new Date();
    }

    public String getId() {
        return id;
    }

    public long getVersion() {
        return version;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }
}
