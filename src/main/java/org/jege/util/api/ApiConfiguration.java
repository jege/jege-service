package org.jege.util.api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * A class extending {@link Application} and annotated with @ApplicationPath is the Java EE 6
 * "no XML" approach to activating JAX-RS.
 * 
 * <p>
 * Resources are served relative to the servlet path specified in the {@link ApplicationPath}
 * annotation.
 * </p>
 */
@ApplicationPath("/api/1")
public class ApiConfiguration extends Application {
   public static final String JSON_EXTENSION = ".json";
   public static final String XML_EXTENSION = ".xml";
}
