package com.authservice.authserver.service;

import com.authservice.authserver.dto.MessageDTO;
import com.authservice.authserver.dto.UsuarioDTO;
import com.authservice.authserver.entity.Role;
import com.authservice.authserver.entity.Usuario;
import com.authservice.authserver.enums.RoleName;
import com.authservice.authserver.repository.RoleRepository;
import com.authservice.authserver.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public MessageDTO createUser(UsuarioDTO usuarioDTO) {
        Usuario usuario = Usuario.builder()
                .username(usuarioDTO.username())
                .password(passwordEncoder.encode(usuarioDTO.password()))
                .build();
        Set<Role> roles = new HashSet<>();
        usuarioDTO.roles().forEach(r -> {
            Role role = roleRepository.findByRoleName(RoleName.valueOf(r))
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            roles.add(role);
        });
        usuario.setRoles(roles);
        usuarioRepository.save(usuario);
        return new MessageDTO("Usuario " + usuario.getUsername() + " saved");
    }
}
