package org.example.awtproject.service;

import lombok.RequiredArgsConstructor;
import org.example.awtproject.dto.UserDTO;
import org.example.awtproject.model.User;
import org.example.awtproject.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


}
