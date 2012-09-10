package org.jege.user.user;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.jege.abs.service.AbstractEntityService;

@Stateless
public class UserService extends AbstractEntityService<User> {
    @Inject
    private Logger log;
    
    public User findByUsername(String username) {
        return createNamedTypedQuery(User.findByUsername).setParameter("username", username).getSingleResult();
    }
}
