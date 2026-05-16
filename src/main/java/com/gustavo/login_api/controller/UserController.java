package com.gustavo.login_api.controller;

import com.gustavo.login_api.dto.UserResponseDTO;
import com.gustavo.login_api.entity.User;
import com.gustavo.login_api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody User user) {

        UserResponseDTO savedUser = service.save(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedUser);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {

        return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO>
    getUserById(@PathVariable Long id) {

        return ResponseEntity.ok(
                service.getUserById(id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {

        service.deleteUser(id);

        return ResponseEntity.ok("Usuario eliminado");
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO>
    updateUser(
            @PathVariable Long id,
            @RequestBody User user) {

        return ResponseEntity.ok(
                service.updateUser(id, user)
        );
    }

}
