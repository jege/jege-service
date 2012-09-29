package org.jege.action.action;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.annotations.Type;
import org.jege.abs.entity.AbstractEntity;
import org.jege.util.internationalization.I18nLabel;

public class Action extends AbstractEntity {
    @Type(type = I18nLabel.TYPE)
    private I18nLabel name;
    
    @Type(type = I18nLabel.TYPE)
    private I18nLabel description;
    
    @Enumerated(EnumType.STRING)
    private Target target;
    
    public enum Target {
        SQUARE,
        INDIVIDUAL,
        PERSONAGE,
        MONSTER,
        BUILDING;
    }
}
