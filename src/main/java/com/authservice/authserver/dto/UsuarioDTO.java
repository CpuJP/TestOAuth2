package com.authservice.authserver.dto;

import java.util.List;

public record UsuarioDTO (
    String username,
    String password,
    List<String> roles){}
