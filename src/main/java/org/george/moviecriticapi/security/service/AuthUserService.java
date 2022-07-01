package org.george.moviecriticapi.security.service;

import lombok.AllArgsConstructor;
import org.george.moviecriticapi.domain.model.UserModel;
import org.george.moviecriticapi.exception.InvalidRequestException;
import org.george.moviecriticapi.repository.UserRepository;
import org.george.moviecriticapi.utils.APIMessages;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class AuthUserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Optional<UserModel> storedUser = Optional.ofNullable(userRepository.findUserModelByUserEmail(userEmail).orElseThrow(
                () -> new InvalidRequestException(APIMessages.INVALID_REQUEST_USER_NOT_FOUND_DSC)
        ));

        return storedUser.map(AuthUserDetail::new).orElse(null);

    }
}
