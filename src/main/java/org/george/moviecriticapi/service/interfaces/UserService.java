package org.george.moviecriticapi.service.interfaces;

import org.george.moviecriticapi.domain.model.UserModel;

import java.util.Collection;

public interface UserService {

    UserModel createUser(UserModel user);
    Collection<UserModel> readAllUsers();
    UserModel readUserById(Long id);
    UserModel readUserByEmail(String email);
    UserModel readUserByToken(String token);
    UserModel updateUser(UserModel user);

    void setUserScore(UserModel user);
}
