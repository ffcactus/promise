package com.promise.platform.auth;

import com.promise.platform.auth.entity.UserEntity;
import com.promise.platform.auth.repository.UserRepository;
import com.promise.platform.auth.sdk.dto.RegisterUserRequestV1;
import com.promise.platform.auth.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private UserRepository userRepository;

    /**
     * This event is executed as late as conceivably possible to indicate that the
     * application is ready to service requests.
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        var root = userRepository.findByName("root");
        if (!root.isPresent()) {
            RegisterUserRequestV1 createRoot = new RegisterUserRequestV1(
                    "root",
                    "iforgot",
                    "root@promise.com");
            createRoot.setPassword(PasswordUtil.encrypt(createRoot.getPassword()));
            var rootUser = new UserEntity(createRoot);
            rootUser.setRole("Admin");
            userRepository.save(rootUser);

            RegisterUserRequestV1 createUser = new RegisterUserRequestV1(
                    "user",
                    "user",
                    "user@promise.com");
            createUser.setPassword(PasswordUtil.encrypt(createUser.getPassword()));
            var commonUser = new UserEntity(createUser);
            commonUser.setRole("User");
            userRepository.save(commonUser);
        }
    }
}