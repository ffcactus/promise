package com.promise.platform.auth.service;

import com.promise.platform.auth.entity.UserEntity;
import com.promise.platform.auth.exception.UserNotExistException;
import com.promise.platform.auth.model.SessionInfo;
import com.promise.platform.auth.repository.SessionRepository;
import com.promise.platform.auth.repository.UserRepository;
import com.promise.platform.auth.sdk.dto.LoginRequestV1;
import com.promise.platform.auth.sdk.exception.UnauthorizedException;
import com.promise.platform.auth.sdk.jwt.JwtTokenGenerator;
import com.promise.platform.auth.sdk.jwt.JwtTokenValidator;
import com.promise.platform.auth.sdk.jwt.JwtUser;
import com.promise.platform.auth.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class SessionService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    JwtTokenGenerator jwtTokenGenerator;

    @Autowired
    private JwtTokenValidator jwtTokenValidator;

    /**
     * Handle login operation.
     *
     * @param request The login request.
     * @return The token string.
     */
    public String Login(LoginRequestV1 request) {
        Optional<UserEntity> user = userRepository.findByName(request.getUsername());
        if (user.isEmpty()) {
            throw new UnauthorizedException();
        }
        UserEntity savedUser = user.get();
        if (PasswordUtil.mismatch(request.getPassword(), savedUser.getPassword())) {
            throw new UnauthorizedException();
        }
        // set token and update it in DB.
        var modelUser = savedUser.toModelUser();
        String token = jwtTokenGenerator.generateToken(modelUser);
        SessionInfo sessionInfo = new SessionInfo(token, modelUser.getId());
        sessionRepository.save(sessionInfo);
        return token;
    }

    /**
     * Logout process, remove the session.
     *
     * @param token
     */
    public void Logout(String token) {
        deleteToken(token);
    }

    public String refreshToken(String token) {
        deleteToken(token);

        JwtUser jwtUser = jwtTokenValidator.parseToken(token);
        var userEntityOpt = userRepository.findById(jwtUser.getId());
        if (userEntityOpt.isEmpty()) {
            throw new UserNotExistException();
        }

        String newToken = jwtTokenGenerator.generateToken(userEntityOpt.get().toModelUser());
        SessionInfo sessionInfo = new SessionInfo(newToken, jwtUser.getId());
        sessionRepository.save(sessionInfo);

        return newToken;
    }

    public JwtUser parseToken(String token) {
        JwtUser jwtUser = jwtTokenValidator.parseToken(token);

        var tokenOpt = sessionRepository.findById(token);
        if (tokenOpt.isEmpty()) {
            throw new UnauthorizedException();
        }

        return jwtUser;
    }

    public void deleteTokens(Long userId) {
        sessionRepository.deleteByUserId(userId);
    }

    private void deleteToken(String token) {
        if (sessionRepository.existsById(token)) {
            sessionRepository.deleteById(token);
        } else {
            throw new UnauthorizedException();
        }
    }

}
