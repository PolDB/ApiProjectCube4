package com.example.contactapp.demo.testsController;

import com.example.contactapp.demo.ControlerApi.AuthController;
import com.example.contactapp.demo.jpa.AuthResponse;
import com.example.contactapp.demo.jpa.LoginRequest;
import com.example.contactapp.demo.jpa.User;
import com.example.contactapp.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthController authController;

    private User validUser;

    @BeforeEach
    void setUp() {
        // Création d'un utilisateur "valide" pour nos tests
        validUser = new User();
        validUser.setUsername("Paul");
        // On hash le mot de passe pour simuler un vrai enregistrement
        validUser.setPassword(BCrypt.hashpw("1234", BCrypt.gensalt()));
    }

    @Test
    void login_shouldReturn200_whenCredentialsAreValid() {
        // Arrange (préparation)
        // On fait en sorte que notre userRepository retourne notre user quand on cherche "Paul"
        when(userRepository.findByUsername("Paul")).thenReturn(validUser);

        // On simule une requête de login
        LoginRequest request = new LoginRequest();
        request.setUsername("Paul");
        request.setPassword("1234");

        // Act (exécution du code à tester)
        ResponseEntity<?> response = authController.login(request);

        // Assert (vérification)
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Vérifions qu'on a bien un objet AuthResponse en retour
        assertTrue(response.getBody() instanceof AuthResponse);
        AuthResponse authResponse = (AuthResponse) response.getBody();
        assertEquals("Paul", authResponse.getUsername());
        assertEquals("1234", authResponse.getPassword());

    }
}