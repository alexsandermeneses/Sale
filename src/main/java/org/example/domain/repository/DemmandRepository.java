package org.example.domain.repository;

import org.example.domain.entity.Client;
import org.example.domain.entity.Demmand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface DemmandRepository extends JpaRepository<Demmand, Integer> {
    List<Demmand> findByClient(Client client);
}