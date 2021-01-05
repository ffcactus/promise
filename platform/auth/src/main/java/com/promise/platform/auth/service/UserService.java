package com.promise.platform.auth.service;

import com.promise.platform.auth.entity.UserEntity;
import com.promise.platform.auth.exception.EmailExistException;
import com.promise.platform.auth.exception.UserNotExistException;
import com.promise.platform.auth.exception.UsernameExistException;
import com.promise.platform.auth.repository.UserRepository;
import com.promise.platform.auth.sdk.dto.GetUserResponseV1;
import com.promise.platform.auth.sdk.dto.RegisterUserRequestV1;
import com.promise.platform.auth.sdk.dto.UpdateUserRequestV1;
import com.promise.platform.auth.sdk.exception.UnauthorizedException;
import com.promise.platform.auth.util.PasswordUtil;
import com.promise.platform.common.exception.InvalidRequestBodyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * The service for user operation.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void register(RegisterUserRequestV1 request) {
        checkUsername(request.getUsername());

        checkEmail(request.getEmail());

        request.setPassword(PasswordUtil.encrypt(request.getPassword()));

        userRepository.save(new UserEntity(request));
    }

    public GetUserResponseV1 getUser(Long userId) {
        var existedUser = userRepository.findById(userId);
        if (existedUser.isEmpty()) {
            throw new UserNotExistException();
        }

        return existedUser.get().toResponseV1();
    }

    @Transactional
    public GetUserResponseV1 updateUser(Long userId, UpdateUserRequestV1 request) {
        if (request == null || userId == null || StringUtils.isEmpty(request.getUsername()) || StringUtils.isEmpty(request.getEmail())) {
            throw new InvalidRequestBodyException();
        }

        var existedUserOpt = userRepository.findById(userId);
        if (existedUserOpt.isEmpty()) {
            throw new UserNotExistException();
        }

        var userEntity = existedUserOpt.get();
        updateUserEntity(userEntity, request);

        return userRepository.save(userEntity).toResponseV1();
    }

    @Transactional
    public void deleteUser(Long userId) {
        if (userId != null)
            userRepository.deleteById(userId);
    }

    @Transactional
    public void changePassword(Long userId, String oldPass, String newPass) {
        checkForChangingPass(userId, oldPass, newPass);

        var userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new UserNotExistException();
        }

        var user = userOpt.get();
        if (!PasswordUtil.mismatch(oldPass, user.getPassword())) {
            throw new UnauthorizedException();
        }

        user.setPassword(PasswordUtil.encrypt(newPass));
        userRepository.save(user);
    }

    @Transactional
    public void resetPassword(Long userId, String newPass) {
        checkForResettingPass(userId, newPass);

        var userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new UserNotExistException();
        }

        var user = userOpt.get();
        user.setPassword(PasswordUtil.encrypt(newPass));
        userRepository.save(user);
    }

    public List<GetUserResponseV1> fetchAllUsers() {
        List<GetUserResponseV1> result = new ArrayList<>();

        List<UserEntity> userEntities = userRepository.findAll();
        userEntities.forEach(ue -> result.add(ue.toResponseV1()));

        return result;
    }

    private UserEntity updateUserEntity(UserEntity userEntity, UpdateUserRequestV1 request) {
        userEntity.setDescription(request.getDescription());
        if (!userEntity.getName().equals(request.getUsername())) {
            checkUsername(request.getUsername());
            userEntity.setName(request.getUsername());
        }
        if (!userEntity.getEmail().equals(request.getEmail())) {
            checkEmail(request.getEmail());
            userEntity.setEmail(request.getEmail());
        }
        userEntity.setRole(request.getRole());
        return userEntity;
    }

    private void checkUsername(String username) {
        var existedUsername = userRepository.findByName(username);
        if (existedUsername.isPresent()) {
            throw new UsernameExistException();
        }
    }

    private void checkEmail(String email) {
        var existedEmail = userRepository.findByEmail(email);
        if (existedEmail.isPresent()) {
            throw new EmailExistException();
        }
    }

    private void checkForChangingPass(Long userId, String oldPass, String newPass) {
        checkForUserId(userId);
        checkForOldPass(oldPass);
        checkForNewPass(newPass);
    }

    private void checkForResettingPass(Long userId, String newPass) {
        checkForUserId(userId);
        checkForNewPass(newPass);
    }

    private void checkForUserId(Long userId) {
        if (userId == null) {
            throw new InvalidRequestBodyException();
        }
    }

    private void checkForOldPass(String oldPass) {
        if (StringUtils.isEmpty(oldPass)) {
            throw new InvalidRequestBodyException();
        }
    }

    private void checkForNewPass(String newPass) {
        if (StringUtils.isEmpty(newPass)) {
            throw new InvalidRequestBodyException();
        }
    }

}
