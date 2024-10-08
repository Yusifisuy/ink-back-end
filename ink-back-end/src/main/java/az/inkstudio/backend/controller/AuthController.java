package az.inkstudio.backend.controller;

import az.inkstudio.backend.dto.request.UserLoginRequest;
import az.inkstudio.backend.dto.request.UserRegisterRequest;
import az.inkstudio.backend.entity.User;
import az.inkstudio.backend.repository.UserRepository;
import az.inkstudio.backend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/security")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterRequest request) {
        // Check if user already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("User already exists with this email");
        }

        // Create a new user and save
        User newUser = new User();
        newUser.setEmail(request.getEmail());
        newUser.setFullname(request.getFullname());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));  // Encrypt password
        userRepository.save(newUser);

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public String auth(@RequestBody UserLoginRequest request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword(),
                            new ArrayList<>())
            );
        } catch (Exception exception) {
            throw new Exception("Invalid username or password");
        }
        return jwtService.generateToken(request.getEmail());
    }
}
