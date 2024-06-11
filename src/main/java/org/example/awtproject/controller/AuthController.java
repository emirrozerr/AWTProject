package org.example.awtproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.awtproject.dto.AuthRequestDTO;
import org.example.awtproject.dto.AuthResponseDTO;
import org.example.awtproject.model.Role;
import org.example.awtproject.model.User;
import org.example.awtproject.repo.RoleRepository;
import org.example.awtproject.repo.UserRepository;
import org.example.awtproject.security.JWTGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository  roleRepository;
    private final JWTGenerator jwtGenerator;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO authRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDTO.getEmail(), authRequestDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        Optional<User> user = userRepository.findByEmail(authRequestDTO.getEmail());
        UUID user_id = user.map(User::getId).orElse(null);
        return ResponseEntity.status(HttpStatus.OK).body(new AuthResponseDTO(token,user_id));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequestDTO authRequestDTO) {
        if (userRepository.existsByEmail(authRequestDTO.getEmail())){
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).body("Email already exists");
        }

        User user = new User();
        user.setEmail(authRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(authRequestDTO.getPassword()));

        Role role = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(role));

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("User Registered");

    }
}
