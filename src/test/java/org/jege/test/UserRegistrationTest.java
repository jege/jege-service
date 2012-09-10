package org.jege.test;

import static org.junit.Assert.assertNotNull;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.core.api.annotation.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jege.user.user.User;
import org.jege.user.user.UserFacade;
import org.jege.user.user.UserService;
import org.jege.util.PersistenceManager;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(Arquillian.class)
public class UserRegistrationTest {
   @Deployment
   public static Archive<?> createTestArchive() {
      return ShrinkWrap.create(WebArchive.class, "test.war")
            .addClasses(User.class, UserService.class, UserFacade.class, PersistenceManager.class)
            .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
            // Deploy our test datasource
            .addAsWebInfResource("test-ds.xml", "test-ds.xml");
   }

   @Inject
   UserFacade userFacade;

   @Inject
   Logger log;

   @Test
   public void testRegister() throws Exception {
      User newUser = new User();
      newUser.setUsername("test");
      newUser.setPassword("test");
      newUser.setEmail("test@test.com");
      userFacade.create(newUser);
      assertNotNull(newUser.getDateCreated());
      log.info(newUser.getUsername() + " was persisted with id " + newUser.getId());
   }
   
}
