package com.example.jsonwebtokenspringsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/home")
public class HomeController {

  @GetMapping
  public String getHome() {
    return "Welcome to Home Page";
  }
}
