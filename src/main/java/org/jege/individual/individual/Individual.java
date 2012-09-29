package org.jege.individual.individual;

import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.MappedSuperclass;

import org.jege.abs.entity.AbstractEntitySuppressible;
import org.jege.individual.skill.Skill;

@MappedSuperclass
public abstract class Individual extends AbstractEntitySuppressible {
    @ElementCollection
    @CollectionTable
    @Column
    @MapKeyJoinColumn
    private Map<Skill, Long> skillProgressions;
}
