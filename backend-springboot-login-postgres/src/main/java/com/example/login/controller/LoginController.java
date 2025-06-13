package com.example.login.controller;

import com.example.login.model.Cliente;
import com.example.login.repository.ClienteRepository;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    private ClienteRepository clienteRepository;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    // INICIAR SESIÓN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Cliente clientes) {
        try {
            logger.info("Intento de login con correo: {}", clientes.getCorreo());

            Preconditions.checkArgument(!Strings.isNullOrEmpty(clientes.getCorreo()), "Correo obligatorio");
            Preconditions.checkArgument(!Strings.isNullOrEmpty(clientes.getPassword()), "Contraseña obligatoria");
            Preconditions.checkArgument(EmailValidator.getInstance().isValid(clientes.getCorreo()), "Correo inválido");

            Optional<Cliente> optionalUsuario = Optional.ofNullable(clienteRepository.findByCorreo(clientes.getCorreo()));

            if (optionalUsuario.isPresent() && optionalUsuario.get().getPassword().equals(clientes.getPassword())) {
                logger.info("Login exitoso para el usuario: {}", optionalUsuario.get().getCorreo());
                return ResponseEntity.ok(optionalUsuario.get());
            } else {
                logger.warn("Login fallido: credenciales incorrectas para el correo {}", clientes.getCorreo());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
            }
        } catch (IllegalArgumentException e) {
            logger.warn("Validación fallida en login: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error interno en login", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor");
        }
    }

    // REGISTRAR CLIENTE
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Cliente cliente) {
        try {
            logger.info("Intento de registro para correo: {}", cliente.getCorreo());

            Preconditions.checkArgument(!Strings.isNullOrEmpty(cliente.getNombre()), "Nombre obligatorio");
            Preconditions.checkArgument(!Strings.isNullOrEmpty(cliente.getApellido()), "Apellido obligatorio");
            Preconditions.checkArgument(!Strings.isNullOrEmpty(cliente.getCorreo()), "Correo obligatorio");
            Preconditions.checkArgument(!Strings.isNullOrEmpty(cliente.getPassword()), "Contraseña obligatoria");

            Optional<Cliente> existente = Optional.ofNullable(clienteRepository.findByCorreo(cliente.getCorreo()));
            if (existente.isPresent()) {
                logger.warn("Registro fallido: correo ya registrado - {}", cliente.getCorreo());
                return ResponseEntity.status(HttpStatus.CONFLICT).body("El correo ya está registrado");
            }

            cliente.setFechaRegistro(LocalDateTime.now());
            Cliente nuevoCliente = clienteRepository.save(cliente);

            logger.info("Registro exitoso para el correo: {}", cliente.getCorreo());
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
        } catch (IllegalArgumentException e) {
            logger.warn("Validación fallida en registro: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error interno al registrar cliente", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar el cliente");
        }
    }
}
