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
    
    public User findByEmail(String email) {
        return getService().findByEmail(email);
    }
    
    public User signIn(String username, String password) {
        User user = getService().findByUsername(username);
        if(user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
