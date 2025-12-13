package dev.tsuda.spring_security.controller;

import dev.tsuda.spring_security.dto.response.LoginResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public LoginResponse test() {
        return new LoginResponse("Teste");
    }
}
