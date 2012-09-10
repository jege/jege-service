package org.jege.abs.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "idName")
})
public class AbstractEntitySuppressibleNamed extends AbstractEntitySuppressible {
    @NotNull
    @Column(unique = true, nullable = false)
    private String idName;

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }
}
