package org.example;

import org.example.domain.entity.Client;
import org.example.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner commandLineRunner(@Autowired ClientRepository clientRepository){
        return args -> {
            Client client = new Client(null, "Cliente1");
            Client client1 = new Client(null, "Cliente2");
            clientRepository.save(client);
            clientRepository.save(client1);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}