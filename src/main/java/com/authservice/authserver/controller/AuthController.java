package com.authservice.authserver.controller;

import com.authservice.authserver.dto.MessageDTO;
import com.authservice.authserver.dto.UsuarioDTO;
import com.authservice.authserver.entity.Usuario;
import com.authservice.authserver.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;

    @PostMapping("/create")
    public ResponseEntity<MessageDTO> createUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.createUser(usuarioDTO));
    }
}
