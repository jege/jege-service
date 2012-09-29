package org.jege.individual.trait;

import javax.persistence.Entity;

import org.hibernate.annotations.Type;
import org.jege.abs.entity.AbstractEntitySuppressibleNamed;
import org.jege.util.internationalization.I18nLabel;

@Entity
public class Trait extends AbstractEntitySuppressibleNamed {
    private String value;
   
    @Type(type = I18nLabel.TYPE)
    private I18nLabel name;
    
    @Type(type = I18nLabel.TYPE)
    private I18nLabel description;
}
