package com.example.Registro.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.Registro.model.Registro;
import com.example.Registro.repository.RegistroRepository;
import com.example.Registro.service.RegistroService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/encomiendas")
@CrossOrigin(origins = "*")
public class RegistroController {

    private final RegistroService registroService;
    private static final Logger logger = LoggerFactory.getLogger(RegistroController.class);

    public RegistroController(RegistroService registroService) {
        this.registroService = registroService;
        
    }
    

    @Autowired
    private RegistroRepository registroRepository;

    @PostMapping
    public ResponseEntity<String> recibirDatos(@RequestBody Registro datos) {
        logger.info("Intento de registrar encomienda de {} {} para {} {}",
            datos.getRemitenteNombre(), datos.getRemitenteApellidoPaterno(),
            datos.getDestinatarioNombre(), datos.getDestinatarioApellidoPaterno());

        // Validación de campos obligatorios
        if (StringUtils.isBlank(datos.getRemitenteNombre()) ||
            StringUtils.isBlank(datos.getRemitenteDNI()) ||
            StringUtils.isBlank(datos.getRemitenteCelular()) ||
            StringUtils.isBlank(datos.getDestinatarioNombre()) ||
            StringUtils.isBlank(datos.getDestinatarioDNI()) ||
            StringUtils.isBlank(datos.getDestinatarioCelular()) ||
            StringUtils.isBlank(datos.getRemitenteApellidoPaterno()) ||
            StringUtils.isBlank(datos.getDestinatarioApellidoPaterno()) ||
            StringUtils.isBlank(datos.getTipoPaquete())) {
            logger.warn("Faltan campos obligatorios en la solicitud de registro");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("Todos los campos obligatorios deben estar llenos.");
        }

        // Validación de DNI
        if (datos.getRemitenteDNI().length() != 8 || datos.getDestinatarioDNI().length() != 8) {
            logger.warn("DNI inválido en el registro: remitente [{}], destinatario [{}]",
                datos.getRemitenteDNI(), datos.getDestinatarioDNI());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("El DNI debe tener 8 dígitos.");
        }

        // Validación de celular
        if (datos.getRemitenteCelular().length() != 9 || datos.getDestinatarioCelular().length() != 9) {
            logger.warn("Celular inválido en el registro: remitente [{}], destinatario [{}]",
                datos.getRemitenteCelular(), datos.getDestinatarioCelular());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("El celular debe tener 9 dígitos.");
        }

        // Validación del tipo de paquete
        Set<String> tiposValidos = new HashSet<>(Arrays.asList(
            "Sobre A4", "Caja S", "Caja M", "Caja XL", "Caja XXL"
        ));
        if (!tiposValidos.contains(datos.getTipoPaquete())) {
            logger.warn("Tipo de paquete inválido: [{}]", datos.getTipoPaquete());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("El tipo de paquete no es válido.");
        }

        try {
            String codigo = registroService.registrar(datos);
            String mensaje = String.format("¡Registro exitoso! Tu código de seguimiento es: %s", codigo);
            return ResponseEntity.ok(mensaje);

        } catch (Exception e) {
            logger.error("Error al registrar la encomienda", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error interno al guardar los datos.");
        }

        
    }
    
}
