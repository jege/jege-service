package org.jege.action.result;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.jege.abs.entity.AbstractEntitySuppressible;
import org.jege.action.recurrentEffect.RecurrentEffect;

@Entity
public class Result extends AbstractEntitySuppressible {
    @ManyToMany
    private Set<RecurrentEffect> recurrentEffects;
}
