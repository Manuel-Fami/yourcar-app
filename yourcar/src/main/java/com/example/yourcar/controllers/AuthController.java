package com.example.yourcar.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.yourcar.entities.Agent;
import com.example.yourcar.entities.User;
import com.example.yourcar.repositories.AgentRepository;
import com.example.yourcar.repositories.UserRepository;
import com.example.yourcar.responses.AuthResponse;
import com.example.yourcar.services.JWTService;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {

	@Autowired
    private JWTService jwtService;
	
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private AgentRepository agentRepository;
	
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> userLogin(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        System.out.println("Email reçu : " + email);
        return authenticateUser(email);
    }

    @PostMapping("/admin/login")
    public ResponseEntity<AuthResponse> adminLogin(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        System.out.println("Email reçu : " + email);
        return authenticateAdmin(email);
    }

    private ResponseEntity<AuthResponse> authenticateUser(String email) {
        User user = userRepository.findByMail(email);

        if (user == null) {
            return ResponseEntity.notFound().build(); 
        }

        String token = jwtService.generateToken(user.mail, "USER");        
        AuthResponse response = new AuthResponse(token, "USER", user.id, user.name);
        return ResponseEntity.ok(response);
    }

    private ResponseEntity<AuthResponse> authenticateAdmin(String email) {
        Agent agent = agentRepository.findByMail(email);

        if (agent == null) {
            return ResponseEntity.notFound().build(); 
        }

        String token = jwtService.generateToken(agent.mail, "ADMIN");        
        AuthResponse response = new AuthResponse(token, "ADMIN", agent.id, agent.name);
        return ResponseEntity.ok(response); 
    }
}