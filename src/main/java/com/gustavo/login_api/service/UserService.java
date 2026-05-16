package com.gustavo.login_api.service;

import com.gustavo.login_api.dto.UserResponseDTO;
import com.gustavo.login_api.entity.User;
import com.gustavo.login_api.exception.ResourceNotFoundException;
import com.gustavo.login_api.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repository,
                       PasswordEncoder encoder) {

        this.repository = repository;
        this.encoder = encoder;
    }

    public UserResponseDTO save(User user) {

        user.setPassword(
                encoder.encode(user.getPassword())
        );

        User savedUser = repository.save(user);

        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getRole()
        );
    }

    public List<UserResponseDTO> getAllUsers() {

        return repository.findAll()
                .stream()
                .map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getUsername(),
                        user.getRole()
                ))
                .toList();
    }

    /*

   public User getUserById(Long id) {

        return repository.findById(id)
                .orElse(null);
    }
    */


    public UserResponseDTO getUserById(Long id) {

        User user = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario no encontrado"
                        ));

        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getRole()
        );
    }

    public void deleteUser(Long id) {

        repository.deleteById(id);
    }

    public UserResponseDTO updateUser(
            Long id,
            User updatedUser) {

        User user = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario no encontrado"
                        ));

        user.setUsername(updatedUser.getUsername());

        user.setRole(updatedUser.getRole());

        User savedUser = repository.save(user);

        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getRole()
        );
    }



}
