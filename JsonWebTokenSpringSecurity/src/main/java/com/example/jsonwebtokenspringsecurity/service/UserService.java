package com.example.jsonwebtokenspringsecurity.service;

import com.example.jsonwebtokenspringsecurity.dto.UserRequest;
import com.example.jsonwebtokenspringsecurity.entity.User;
import com.example.jsonwebtokenspringsecurity.entity.enums.Role;
import com.example.jsonwebtokenspringsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JWTService jwtService;

  public User createUser(UserRequest request) {
    User user = new User();
    user.setUsername(request.getUsername());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setRole(Role.USER);
    return userRepository.save(user);
  }

  public String authenticate(UserRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
    User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
    return jwtService.generateToken(user);
  }
}
