package org.jege.user;

import org.jege.user.user.User;
import org.jege.user.user.UserMixin;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class UserModule extends SimpleModule {
    public UserModule() {
        super("UserModule");
    }

    @Override
    public void setupModule(SetupContext context) {
        context.setMixInAnnotations(User.class, UserMixin.class);
    }
}
