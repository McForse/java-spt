package ru.mirea.pr24.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.pr24.dto.SignUpRequest;
import ru.mirea.pr24.exceptions.UserAlreadyExistException;
import ru.mirea.pr24.models.User;
import ru.mirea.pr24.models.UserPrincipal;
import ru.mirea.pr24.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User registerNewUserAccount(SignUpRequest request) throws UserAlreadyExistException {
        if (exist(request.getUsername())) {
            throw new UserAlreadyExistException(
                    "There is an account with that username: "
                            + request.getUsername());
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return new UserPrincipal(user);
    }

    public boolean exist(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
