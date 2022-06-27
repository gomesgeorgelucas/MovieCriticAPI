package org.george.moviecriticapi.controller;

import lombok.AllArgsConstructor;
import org.george.moviecriticapi.domain.model.UserModel;
import org.george.moviecriticapi.service.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/mcapi/user")
@AllArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<?> readAllUsers() {
        return ResponseEntity.ok().body(userService.readAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> readUserById(
            @PathVariable(name = "userId") Long userId) {
        return ResponseEntity.ok().body(userService.readUserById(userId));
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UserModel user) {
        return ResponseEntity.ok().body(userService.createUser(user));
    }
}
