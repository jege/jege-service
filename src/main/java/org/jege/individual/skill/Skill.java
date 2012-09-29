package org.jege.individual.skill;

import javax.persistence.Entity;

import org.hibernate.annotations.Type;
import org.jege.abs.entity.AbstractEntitySuppressibleNamed;
import org.jege.util.internationalization.I18nLabel;

@Entity
public class Skill extends AbstractEntitySuppressibleNamed {
    @Type(type = I18nLabel.TYPE)
    private I18nLabel name;
    
    @Type(type = I18nLabel.TYPE)
    private I18nLabel description;
    
    private String prerequisite;
}
