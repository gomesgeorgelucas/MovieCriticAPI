package org.george.moviecriticapi.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import lombok.AllArgsConstructor;
import org.george.moviecriticapi.config.JWTPropertiesConfig;
import org.george.moviecriticapi.domain.enums.UserRoleEnum;
import org.george.moviecriticapi.domain.model.UserModel;
import org.george.moviecriticapi.exception.InvalidRequestException;
import org.george.moviecriticapi.repository.UserRepository;
import org.george.moviecriticapi.service.interfaces.UserService;
import org.george.moviecriticapi.utils.APIMessages;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    private final JWTPropertiesConfig properties;

    @Override
    public UserModel createUser(UserModel user) {
        if (userRepository
                .findUserModelByUserEmail(user.getUserEmail()).isPresent()) {
            throw new InvalidRequestException(APIMessages.INVALID_REQUEST_EMAIL_ALREADY_REGISTERED_DSC);
        }

        if (user.getUserRole() != UserRoleEnum.READER) {
            throw new InvalidRequestException(APIMessages.INVALID_REQUEST_INCORRECT_PROFILE_DSC);
        }

        if (user.getUserScore() > 0) {
            throw new InvalidRequestException(APIMessages.INVALID_REQUEST_INVALID_SCORE_DSC);
        }

        user.setUserPassword(encoder.encode(user.getUserPassword()));

        return userRepository.save(user);
    }
    @Override
    public Collection<UserModel> readAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserModel readUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new InvalidRequestException(APIMessages.INVALID_REQUEST_USER_NOT_FOUND_DSC));
    }

    @Override
    public UserModel readUserByEmail(String userEmail) {
        return userRepository.findUserModelByUserEmail(userEmail).orElseThrow(
                () -> new InvalidRequestException(APIMessages.INVALID_REQUEST_USER_NOT_FOUND_DSC)
        );
    }

    @Override
    public UserModel readUserByToken(String token) {
        try {
            String userEmail = JWT.require(Algorithm.HMAC512(properties.getTokenSecret()))
                    .build()
                    .verify(token)
                    .getSubject();
            return readUserByEmail(userEmail);
        } catch (SignatureVerificationException e) {
            throw new InvalidRequestException(APIMessages.INVALID_JWT_TOKEN_DSC);
        }
    }

    @Override
    @Transactional
    public UserModel updateUser(UserModel user) {
        return userRepository.save(user);
    }

    @Override
    public void setUserScore(UserModel user) {
        updateUser(updateUserRole(updateScore(user)));
    }

    private UserModel updateUserRole(UserModel user) {
        if (user.getUserScore() >= 20 && user.getUserScore() < 100) {
            user.setUserRole(UserRoleEnum.BASIC);
        } else if (user.getUserScore() >= 100 && user.getUserScore() < 1000) {
            user.setUserRole(UserRoleEnum.ADVANCED);
        } else if (user.getUserScore() >= 1000) {
            user.setUserRole(UserRoleEnum.MODERATOR);
        }

        return user;
    }

    private UserModel updateScore(UserModel user) {
        Long userScore = user.getUserScore();
        userScore += 1;
        user.setUserScore(userScore);

        return user;
    }
}
