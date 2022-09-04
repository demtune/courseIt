package com.example.demoit2.controller.auth;

import com.example.demoit2.entity.users.UserEntity;
import com.example.demoit2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<?> login(Principal principal) {
        Optional<UserEntity> optUser = Optional.ofNullable(userService.getUser(principal.getName()));
        if (optUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        UserEntity user = optUser.get();
        user.setLastLogin(LocalDateTime.now());
        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
