package org.example.rest.controller;

import org.example.domain.entity.Client;
import org.example.domain.repository.ClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
public class ClientController {

    private ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @PostMapping("/api/clients")
    @ResponseBody
    public ResponseEntity save( @RequestBody Client client){
        Client clientSave = clientRepository.save(client);
        return ResponseEntity.ok(clientSave);
    }

    @GetMapping("/api/clients/{id}")
    @ResponseBody
    public ResponseEntity getClientById( @PathVariable Integer id){
        Optional<Client> client = clientRepository.findById(id);

        if (client.isPresent()){
            return ResponseEntity.ok(client.get());
        }

        return ResponseEntity.notFound().build();
    }
}