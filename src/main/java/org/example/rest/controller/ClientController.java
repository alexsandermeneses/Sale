package org.example.rest.controller;

import org.example.domain.entity.Client;
import org.example.domain.repository.ClientRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client save( @RequestBody Client client){
        return clientRepository.save(client);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id,
                       @RequestBody Client client){
        clientRepository
                .findById(id)
                .map(clientExist -> {
                    client.setId(clientExist.getId());
                    clientRepository.save(client);
                    return clientExist;
                }).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Customer not found"));
    }

    @GetMapping
    public List<Client> findAll(Client filter){
        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING);
        Example example = Example.of(filter, exampleMatcher);
        return clientRepository.findAll(example);
    }

    @GetMapping("{id}")
    public Client getClientById( @PathVariable Integer id){
        return clientRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Customer not found"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        clientRepository.findById(id)
                .map(client -> {
                    clientRepository.delete(client);
                    return client;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Customer not found"));
    }
}