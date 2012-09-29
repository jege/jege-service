package org.jege.util;

public class PersonaHelper {
    public enum VerifyStatus {
        OKAY("okay"),
        FAILURE("failure");
        
        private VerifyStatus(String name) {
            this.name = name;
        }
        
        private String name;
        
        public String getName() {
            return name;
        }
    }
    
    public enum VerifyParam {
        STATUS("status", true, true),
        EMAIL("email", true, false),
        AUDIENCE("audience", true, false),
        EXPIRES("expires", true, false),
        ISSUER("issuer", true, false),
        REASON("reason", false, true);
        
        private VerifyParam(String name, boolean onSuccess, boolean onError) {
            this.name = name;
            this.onSuccess = onSuccess;
            this.onError = onError;
        }
        
        private String name;
        private boolean onSuccess;
        private boolean onError;
        
        public String getName() {
            return name;
        }
        
        public boolean isOnSuccess() {
            return onSuccess;
        }
        
        public boolean isOnError() {
            return onError;
        }
    }
}
