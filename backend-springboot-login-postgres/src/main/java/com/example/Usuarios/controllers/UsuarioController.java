package com.example.Usuarios.controllers;

import java.util.ArrayList;
import java.util.Optional;

import com.google.common.base.Preconditions;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.Usuarios.models.UsuarioModel;
import com.example.Usuarios.services.UsuarioService;

@RestController
@RequestMapping("/empleados")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    UsuarioService usuarioService;

    @GetMapping()
    public ArrayList<UsuarioModel> obtenerUsuarios() {
        return usuarioService.obtenerUsuarios();
    }

    @PostMapping()
    public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario) {
        Preconditions.checkNotNull(usuario.getEmail(), "El email no puede ser nulo");

        if (!EmailValidator.getInstance().isValid(usuario.getEmail())) {
            throw new IllegalArgumentException("Email inválido");
        }

        if (usuario.getDni() == null || usuario.getDni().length() != 8) {
            throw new IllegalArgumentException("DNI inválido, debe tener 8 caracteres");
        }

        return usuarioService.guardarUsuario(usuario);
    }

    @PutMapping("/{id}")
    public UsuarioModel actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioModel usuario) {
        usuario.setIdcliente(id);
        return usuarioService.guardarUsuario(usuario);
    }

    @GetMapping(path = "/{id}")
    public Optional<UsuarioModel> obtenerUsuarioPorId(@PathVariable("id") Long id) {
        return usuarioService.obtenerPorId(id);
    }

    @GetMapping("/query")
    public ArrayList<UsuarioModel> obtenerUsuarioPorPrioridad(@RequestParam("prioridad") Integer prioridad) {
        return usuarioService.obtenerPorPrioridad(prioridad);
    }

    @DeleteMapping(path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id) {
        boolean ok = usuarioService.eliminarUsuario(id);
        if (ok) {
            return "Se eliminó el usuario con id " + id;
        } else {
            return "No se pudo eliminar el usuario con id " + id;
        }
    }
}
