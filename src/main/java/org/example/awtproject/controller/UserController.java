package org.example.awtproject.controller;


import lombok.RequiredArgsConstructor;
import org.example.awtproject.model.Workspace;
import org.example.awtproject.repo.UserRepository;
import org.example.awtproject.model.User;
import org.example.awtproject.repo.WorkspaceRepository;
import org.example.awtproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping("api/users")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id){
        Optional<User> user = userRepository.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user.orElse(null));
    }

}
