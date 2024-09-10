package org.example.domain.repository;

import org.example.domain.entity.Client;
import org.example.domain.entity.Demmand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DemmandRepository extends JpaRepository<Demmand, Integer> {
    List<Demmand> findByClient(Client client);

    @Query("select d from Demmand d left join fetch d.itemOrders where d.id = :id")
    Optional<Demmand> findByIdFetchItemOrders(@Param("id") Integer id);
}