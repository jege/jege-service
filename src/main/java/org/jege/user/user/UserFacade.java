package org.jege.user.user;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.jege.abs.facade.AbstractEntityFacade;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class UserFacade extends AbstractEntityFacade<User> {
    @Inject
    private UserService userService;
    
    @Override
    protected UserService getService() {
        return userService;
    }
    
    public String signIn(String username, String password) {
        String result = null;
        User user = getService().findByUsername(username);
        
        if(user != null && user.getPassword().equals(password)) {
            result = user.getId();
        }
        
        return result;
    }
}
