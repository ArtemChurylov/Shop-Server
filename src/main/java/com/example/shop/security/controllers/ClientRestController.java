package com.example.shop.security.controllers;

import com.example.shop.application.models.Product;
import com.example.shop.application.service.ProductService;
import com.example.shop.security.models.Client;
import com.example.shop.security.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/service/client")
public class ClientRestController {

    private final ClientService clientService;
    private final ProductService productService;

    public ClientRestController(ClientService clientService, ProductService productService) {
        this.clientService = clientService;
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();

        for (Client c : clients) {
            c.setOrders(null);
        }

        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Optional<Client> client = clientService.getClientById(id);
        if (client.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        client.get().setOrders(null);
        return new ResponseEntity<>(client.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Client> saveClient(@RequestBody Client client) {
        if (client == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        clientService.saveClient(client);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @PutMapping
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {
        if (client == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Client dbClient = clientService.getClientById(client.getId()).orElseThrow();

        Product product = client.getOrders().get(0);
        product.getCustomers().add(dbClient);
        dbClient.getOrders().add(product);
        clientService.saveClient(dbClient);
        productService.saveProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> deleteClientById(@PathVariable Long id) {
        Optional<Client> client = clientService.getClientById(id);
        if (client.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        clientService.deleteClientById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}














