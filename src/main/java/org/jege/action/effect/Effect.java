package org.jege.action.effect;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.jege.individual.individual.Individual;
import org.jege.individual.personage.Personage;
import org.jege.individual.skill.Skill;
import org.jege.individual.trait.Trait;
import org.jege.map.square.Square;

public abstract class Effect {
    public enum Param {
        CURRENT_PERSONAGE(Personage.class),
        TARGET_SQUARE(Square.class),
        TARGET_INDIVIDUAL(Individual.class),
        TARGET_PERSONAGE(Personage.class),
        IMPACTED_TRAIT(Trait.class),
        IMPACTED_SKILL(Skill.class);
        
        private Param(Class clazz) {
            this.clazz = clazz;
        }
        
        private Class clazz;
        
        public Class getClazz() {
            return clazz;
        }
    }
    
    public abstract void apply(Map<Param, Object> args);
    
    public abstract Set<Param> getParams();
    public abstract Set<Param> getRequiredParams();
    
    public boolean isValidParams(Map<Param, Object> args) {
        for(Entry<Param, Object> param : args.entrySet()) {
            if(!isValidParam(param.getKey(), param.getValue())) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isValidParam(Param param, Object value) {
        return getParams().contains(param) &&
                (value == null ? 
                        !getRequiredParams().contains(param) 
                        : value.getClass().equals(param.getClazz()));
    }
}
