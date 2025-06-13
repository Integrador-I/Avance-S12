package com.example.Usuarios.services;

import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Usuarios.models.UsuarioModel;
import com.example.Usuarios.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    UsuarioRepository usuarioRepository;

    public ArrayList<UsuarioModel> obtenerUsuarios() {
        logger.info("Listando todos los usuarios");
        return (ArrayList<UsuarioModel>) usuarioRepository.findAll();
    }

    public UsuarioModel guardarUsuario(UsuarioModel usuario) {
        logger.info("Guardando usuario con email: {}", usuario.getEmail());
        return usuarioRepository.save(usuario);
    }

    public Optional<UsuarioModel> obtenerPorId(Long id) {
        logger.info("Obteniendo usuario por ID: {}", id);
        return usuarioRepository.findById(id);
    }

    public ArrayList<UsuarioModel> obtenerPorPrioridad(Integer prioridad) {
        logger.info("Obteniendo usuarios con prioridad: {}", prioridad);
        return usuarioRepository.findByPrioridad(prioridad);
    }

    public boolean eliminarUsuario(Long id) {
        try {
            usuarioRepository.deleteById(id);
            logger.info("Usuario con ID {} eliminado", id);
            return true;
        } catch (Exception err) {
            logger.error("Error al eliminar usuario con ID {}: {}", id, err.getMessage());
            return false;
        }
    }
}
