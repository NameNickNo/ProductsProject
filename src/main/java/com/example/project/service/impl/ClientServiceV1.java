package com.example.project.service.impl;

import com.example.project.model.Client;
import com.example.project.repository.ClientRepository;
import com.example.project.service.ClientService;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceV1 implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceV1(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client findByName(String name) {
        return clientRepository.findClientByName(name);
    }

    @Override
    public void save(Client client) {
        clientRepository.save(client);
    }
}
