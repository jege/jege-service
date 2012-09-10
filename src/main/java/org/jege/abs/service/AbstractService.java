package org.jege.abs.service;

import javax.inject.Inject;

import org.jege.util.PersistenceManager;

public class AbstractService {
    @Inject
    private PersistenceManager persistenceManager;
    
    protected PersistenceManager getPersistenceManager() {
        return persistenceManager;
    }
}
