package com.example.project.service;

import com.example.project.model.Client;

public interface ClientService {

    Client findByName(String name);

    void save(Client client);
}
