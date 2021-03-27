package com.example.shop.security.service;

import com.example.shop.security.models.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    void saveClient(Client client);
    List<Client> getAllClients();
    Optional<Client> getClientById(Long id);
    void deleteClientById(Long id);
}
