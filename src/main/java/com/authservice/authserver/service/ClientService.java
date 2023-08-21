package com.authservice.authserver.service;

import com.authservice.authserver.dto.ClientDTO;
import com.authservice.authserver.dto.MessageDTO;
import com.authservice.authserver.entity.Client;
import com.authservice.authserver.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientService implements RegisteredClientRepository {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void save(RegisteredClient registeredClient) {

    }

    @Override
    public RegisteredClient findById(String id) {
        Client client = clientRepository.findByClientId(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        return Client.toRegisteredClient(client);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        Client client = clientRepository.findByClientId(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        return Client.toRegisteredClient(client);
    }

    public MessageDTO create(ClientDTO dto) {
        Client client = clientFromDto(dto);
        clientRepository.save(client);
        return new MessageDTO(String.format("Client %s saved", client.getClientId()));
    }

    private Client clientFromDto(ClientDTO clientDTO) {
        Client client = Client.builder()
                .clientId(clientDTO.getClientId())
                .clientSecret(passwordEncoder.encode(clientDTO.getClientSecret()))
                .clientAuthenticationMethods(clientDTO.getClientAuthenticationMethods())
                .authorizationGrantTypes(clientDTO.getAuthorizationGrantTypes())
                .redirectUris(clientDTO.getRedirectUris())
                .scopes(clientDTO.getScopes())
                .requireProofKey(clientDTO.isRequireProofKey())
                .build();
        return client;
    }
}
