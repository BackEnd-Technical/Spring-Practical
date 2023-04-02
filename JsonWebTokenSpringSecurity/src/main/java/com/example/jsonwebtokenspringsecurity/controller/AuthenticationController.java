package com.example.jsonwebtokenspringsecurity.controller;

import com.example.jsonwebtokenspringsecurity.dto.UserRequest;
import com.example.jsonwebtokenspringsecurity.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/auth")
public class AuthenticationController {

  private final UserService userService;

  @PostMapping
  public ResponseEntity<String> login(
      @RequestBody UserRequest request, HttpServletResponse httpServletResponse) {
    String token = userService.authenticate(request);
    return ResponseEntity.ok().body(token);
  }
}
