package com.auctionapp.controller;

import com.auctionapp.auth.AuthService;
import com.auctionapp.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Authentication API specification for Swagger documentation. Implemented by
 * Spring Security.
 *
 * @author Ensar Horozović
 */
@RestController
@CrossOrigin
@RequestMapping("/")
public class AuthController extends BaseController {

    @Autowired
    private AuthService authService;

    @ApiResponses({ @ApiResponse(code = 401, message = "Unauthorized") })
    @PostMapping("/login")
    public Authentication login(@RequestParam String username, @RequestParam String password) {
        throw new IllegalStateException("Add Spring Security to handle authentication");
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/logout")
    public void logout() {
        throw new IllegalStateException("Add Spring Security to handle authentication");
    }

    @GetMapping("/login")
    public String showLoginPage() {

        return "forward:/";
    }
}
